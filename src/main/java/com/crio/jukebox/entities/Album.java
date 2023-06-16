package com.crio.jukebox.entities;

import java.util.List;
import java.util.ArrayList;

public class Album extends BaseEntity {
	private final List<Song> songs;
	private final Artist owner;
	

	public Album(Album other) {
		this(other.id, other.name, other.songs, other.owner);
	}

    public Album(String albumName, String artistName) {
        this.name = albumName;
        this.songs = new ArrayList<Song> ();
        this.owner = new Artist(artistName);
    }
	
	public Album(String Id, String name, List<Song> songs, Artist owner) {
		this.id = Id;
		this.name = name;
		this.songs = songs;
		this.owner = owner;
	}

    public List<Song> getSongs() {
		return songs;
	}

	public Artist getOwner() {
		return owner;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((songs == null) ? 0 : songs.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Album other = (Album) obj;
        if (owner == null) {
            if (other.owner != null)
                return false;
        } else if (!owner.equals(other.owner))
            return false;
        if (songs == null) {
            if (other.songs != null)
                return false;
        } else if (!songs.equals(other.songs))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Album [owner=" + owner + ", songs=" + songs + "]";
    }
	
}
