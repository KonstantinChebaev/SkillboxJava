package main.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "is_active", nullable = false, columnDefinition = "TINYINT")
    private boolean isActive;
    @Column(name = "moderation_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ModerStat moderStat;
    public enum ModerStat {NEW, ACCEPTED, DECLINED}
    @Column(name = "moderator_id", nullable = false)
    private int moderatorId;
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Column(nullable = false, columnDefinition = "DATETIME")
    private Date time;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;
    @Column(name = "view_count", nullable = false)
    private int viewCount;

    public Post() {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public ModerStat getModerStat() {
        return moderStat;
    }

    public void setModerStat(ModerStat moderStat) {
        this.moderStat = moderStat;
    }

    public int getModeratorId() {
        return moderatorId;
    }

    public void setModeratorId(int moderatorId) {
        this.moderatorId = moderatorId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
