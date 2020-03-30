package main.model;


import javax.persistence.*;

@Entity
@Table(name="tag2post")
public class TagToPost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "post_id", nullable = false)
    private int postId;
    @Column(name = "tag_id", nullable = false)
    private int tagId;

    public TagToPost (){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }
}
