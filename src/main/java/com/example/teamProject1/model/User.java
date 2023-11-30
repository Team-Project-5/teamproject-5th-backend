package com.example.teamProject1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {
    public interface JsonViewWithoutPassword {}
    public interface JsonViewWithPassword extends JsonViewWithoutPassword {}

    @Id //Primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @JsonView(JsonViewWithoutPassword.class)
    @Column(nullable = false, length = 100, unique = true)
    private String username; // 아이디

    @JsonView(JsonViewWithPassword.class)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 100) // 123456 => 해쉬로 변경(비밀번호 암호화)
    private String password;

    @JsonView(JsonViewWithoutPassword.class)
    @Column(nullable = false, length = 20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;
}