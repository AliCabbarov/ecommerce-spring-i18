package delivery.management.system.model.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleRequestDto {
    private String name;

    private List<Long> permissions;
}
