package uz.tafakkoor.easyorder.domains.image;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileUpload {
    private String fileName;
    private String fileType;
    private byte[] fileData;
}

