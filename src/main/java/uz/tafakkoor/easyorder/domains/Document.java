package uz.tafakkoor.easyorder.domains;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Document extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String originalName;
    @Column(nullable = false)
    private String generatedName;
    @Column(nullable = false)
    private String extension;
    @Column(nullable = false)
    private String mimeType;
    @Column(nullable = false)
    private Long size;

    @Builder(builderMethodName = "documentBuilder")
    public Document(Long createdBy, Long updateBy, LocalDateTime createdAt, LocalDateTime updatedAt,
                    boolean isDeleted, Long id, String originalName, String generatedName, String extension,
                    String mimeType, Long size) {
        super(createdBy, updateBy, createdAt, updatedAt, isDeleted);
        this.id = id;
        this.originalName = originalName;
        this.generatedName = generatedName;
        this.extension = extension;
        this.mimeType = mimeType;
        this.size = size;
    }

}