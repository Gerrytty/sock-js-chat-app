package app.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "chat_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chat_user_id;

    private String username;
    private String password;


}
