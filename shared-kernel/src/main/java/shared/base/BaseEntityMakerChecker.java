package shared.base;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import shared.enums.Status;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@ToString
//Base class for entities that requires maker checker
public class BaseEntityMakerChecker extends BaseEntity {
    @Column(nullable = false)
    private Status status;
    private String rejectReason;
    private String statusUpdatedBy;
    private String statusUpdatedOn;
}
