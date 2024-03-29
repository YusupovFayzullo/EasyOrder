package uz.tafakkoor.easyorder.services.auth;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.tafakkoor.easyorder.config.security.JwtUtils;
import uz.tafakkoor.easyorder.config.security.SessionUser;
import uz.tafakkoor.easyorder.domains.menu.Basket;
import uz.tafakkoor.easyorder.domains.user.OTP;
import uz.tafakkoor.easyorder.domains.user.OTP.OtpType;
import uz.tafakkoor.easyorder.domains.user.User;
import uz.tafakkoor.easyorder.dtos.auth.RefreshTokenRequest;
import uz.tafakkoor.easyorder.dtos.auth.TokenRequest;
import uz.tafakkoor.easyorder.dtos.auth.TokenResponse;
import uz.tafakkoor.easyorder.dtos.auth.UserCreateDTO;
import uz.tafakkoor.easyorder.enums.TokenType;
import uz.tafakkoor.easyorder.enums.UserStatus;
import uz.tafakkoor.easyorder.exceptions.ItemNotFoundException;
import uz.tafakkoor.easyorder.exceptions.OTPExpiredException;
import uz.tafakkoor.easyorder.mappers.user.UserMapper;
import uz.tafakkoor.easyorder.repositories.BasketRepository;
import uz.tafakkoor.easyorder.repositories.OTPRepository;
import uz.tafakkoor.easyorder.repositories.user.UserRepository;
import uz.tafakkoor.easyorder.repositories.user.UserRolesRepository;
import uz.tafakkoor.easyorder.utils.BaseUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionUser sessionUser;
    private final BaseUtils utils;
    private final JwtUtils jwtUtils;
    private final UserRolesRepository userRolesRepository;
    private final OTPRepository otpRepository;
    private final TwilioService twilioService;
    @Value("${twilio.activation.code.expiry}")
    private int activationCodeExpiry;
    private static final String basePATH = "http://localhost:8080/api/v1/auth/activate/";
    private final BasketRepository basketRepository;

    public TokenResponse generateToken(@NonNull TokenRequest tokenRequest) {
        String phoneNumber = tokenRequest.phoneNumber();
        String password = tokenRequest.password();
        this.userRepository.findByPhoneNumberLike(phoneNumber).orElseThrow(() -> new ItemNotFoundException("User not found"));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(phoneNumber, password);
        this.authenticationManager.authenticate(authentication);
        return this.jwtUtils.generateToken(phoneNumber);
    }

    public User create(@NotNull UserCreateDTO dto) {
        User user = UserMapper.INSTANCE.toEntity(dto);
        user.setPassword(this.passwordEncoder.encode(dto.password()));
        user.setRoles(Collections.singletonList(this.userRolesRepository.findByCode("USER")));
        User saved_user = this.userRepository.save(user);
        String code = this.utils.generateOTP();
        CompletableFuture.runAsync(() -> {
            OTP Otp = OTP.childBuilder().userID(saved_user.getId()).code(code).expiresAt(LocalDateTime.now().plusMinutes(this.activationCodeExpiry)).otpType(OtpType.ACCOUNT_ACTIVATE).build();
            this.otpRepository.save(Otp);
            this.twilioService.sendOtp(saved_user.getPhoneNumber(), code);
        });
        return saved_user;
    }

    public TokenResponse refreshToken(@NotNull RefreshTokenRequest refreshTokenRequest) {

        String refreshToken = refreshTokenRequest.refreshToken();
        if (!this.jwtUtils.isTokenValid(refreshToken, TokenType.REFRESH))
            throw new CredentialsExpiredException("Token is invalid");

        String email = this.jwtUtils.getUsername(refreshToken, TokenType.REFRESH);
        this.userRepository.findByPhoneNumber(email);
        TokenResponse tokenResponse = TokenResponse.builder().refreshToken(refreshToken).refreshTokenExpiry(this.jwtUtils.getExpiry(refreshToken, TokenType.REFRESH)).build();
        return this.jwtUtils.generateAccessToken(email, tokenResponse);
    }

    public String activate(String code, String phoneNumber) {
        User user = this.userRepository.findByPhoneNumberLike(phoneNumber).orElseThrow(() -> new ItemNotFoundException("User not found"));
        OTP code1 = this.otpRepository.findByUserIDAndCode(user.getId(), code).orElseThrow(() -> new ItemNotFoundException("Invalid code"));
        if (code1.getExpiresAt().isBefore(LocalDateTime.now())) throw new OTPExpiredException("Code expired");
        user.setStatus(UserStatus.ACTIVE);
        Basket basket = Basket.basketBuilder()
                .owner(user)
                .createdBy(user.getId())
                .build();
        basketRepository.save(basket);
        this.userRepository.save(user);
        return "Account activated";
    }

    public AuthService(final AuthenticationManager authenticationManager, final UserMapper userMapper, final UserRepository userRepository, final PasswordEncoder passwordEncoder, final SessionUser sessionUser, final BaseUtils utils, final JwtUtils jwtUtils, final UserRolesRepository userRolesRepository, final OTPRepository otpRepository, final TwilioService twilioService,
                       BasketRepository basketRepository) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionUser = sessionUser;
        this.utils = utils;
        this.jwtUtils = jwtUtils;
        this.userRolesRepository = userRolesRepository;
        this.otpRepository = otpRepository;
        this.twilioService = twilioService;
        this.basketRepository = basketRepository;
    }
}
