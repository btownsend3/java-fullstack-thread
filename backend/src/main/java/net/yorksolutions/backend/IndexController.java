package net.yorksolutions.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@CrossOrigin
public class IndexController {

    private final AuthService authService;
    private final ForumService forumService;
    @Autowired
    public IndexController(@NonNull AuthService authService, @NonNull ForumService forumService){
        this.authService = authService;
        this.forumService = forumService;
    }

    @GetMapping("/hello")
    public String hello(){
        return "Hello World";
    }

    @GetMapping("/login")
    public UUID login(@RequestParam String username, @RequestParam String password){
        return this.authService.login(username, password);
    }
    @GetMapping("/logout")
    public void logout(@RequestParam UUID token){
        this.authService.logout(token);
    }
    @GetMapping("/signup")
    public void signup(@RequestParam String username, @RequestParam String password){
        this.authService.signup(username, password);
    }

    @GetMapping("/createForumThread")
    public void createForumThread(
            @RequestParam UUID token,
            @RequestParam String title,
            @RequestParam String description){
        UUID loggedUser = this.authService.checkAuth(token);
        this.forumService.create(loggedUser, title, description);
    }

    @GetMapping("/forumThreads")
    public Iterable<ForumThread> forumThreads(){
        return this.forumService.getList();
    }
}
