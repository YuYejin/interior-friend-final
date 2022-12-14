package com.inf.khproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "writer")
public class Notice extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeNo;

    @Column(nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Member writer;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeContent(String content) {
        this.content = content;
    }

}
