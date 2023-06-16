package com.crio.jukebox.entities;


import java.util.List;
import java.util.ArrayList;

public class Artist extends BaseEntity {
	private final List<Album> albums;
	
	public Artist(Artist other) {
		this(other.id, other.name, other.albums);
	}

    public Artist(String name) {
        this.name = name;
        albums = new ArrayList<Album>();
    }
	
	Artist(String Id, String name, List<Album> albums) {
		this.id = Id;
		this.name = name;
		this.albums = albums;
	}
	
	public List<Album> getAlbums() {
		return albums;
	}

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((albums == null) ? 0 : albums.hashCode());
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
        Artist other = (Artist) obj;
        if (albums == null) {
            if (other.albums != null)
                return false;
        } else if (!albums.equals(other.albums))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Artist [albums=" + albums + "]";
    }


}
