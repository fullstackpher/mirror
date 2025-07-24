package cn.vgonet.mirror.user.gateways.ohs;

import cn.vgonet.mirror.user.application.*;
import com.google.common.collect.ImmutableMap;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {
    private final RegisterUserUseCase registerUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final GetUserProfileUseCase getUserProfileUseCase;

    public UserController(RegisterUserUseCase registerUserUseCase,
                          LoginUserUseCase loginUserUseCase,
                          GetUserProfileUseCase getUserProfileUseCase) {
        this.registerUserUseCase = registerUserUseCase;
        this.loginUserUseCase = loginUserUseCase;
        this.getUserProfileUseCase = getUserProfileUseCase;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Object register(@RequestBody RegisterUserInput input) {
        return ImmutableMap.of("id", registerUserUseCase.execute(input));
    }

    @PostMapping("/login")
    public Object login(@RequestBody LoginUserInput input) {
        return loginUserUseCase.execute(input);
    }

    @GetMapping("/profile/{id}")
    public Object getProfile(@PathVariable String id) {
        return getUserProfileUseCase.execute(id);
    }
}
