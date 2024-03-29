package delivery.management.system.controller;

import delivery.management.system.model.dto.request.RoleRequestDto;
import delivery.management.system.model.dto.response.RoleResponseDto;
import delivery.management.system.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/roles")
public class RoleController {

    private final RoleService roleService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody RoleRequestDto roleRequestDto) {

        return roleService.create(roleRequestDto);
    }

    @PatchMapping
    public ResponseEntity<Void> update(@RequestBody RoleRequestDto roleRequestDto) {

        return roleService.update(roleRequestDto);
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> roles() {

        return roleService.findAllRoles();
    }

}
