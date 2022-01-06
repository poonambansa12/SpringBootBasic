package com.example.persistence.entity;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false)
    private String author;

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }

        Book other = (Book) obj;
        if (id != other.id) {
            return false;
        }

        if (author == null) {
            if (other.getAuthor() != null) {
                return false;
            }
        else if (!author.equals(other.author)) {
            return false;
            }
        }

        if (title == null) {
            return other.title == null;
        }
        else return title.equals(other.title);
    }

    @Override
    public String toString() {
        return "Book [ id: " + id + ", Title: " + title +", Author: " + author + "]";
    }
}
