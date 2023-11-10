package DDuDu.DDuDu.dto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserRequest {
    @NotBlank(message = "이메일 주소를 입력해주세요.")
    @Email(message = "올바른 이메일 주소를 입력해주세요.")
    private String email;
    private String username;
    private String password;
}
