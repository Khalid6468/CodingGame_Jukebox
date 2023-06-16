package com.crio.jukebox.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Song extends BaseEntity {
	private List<Artist> featuredArtists;
	private Album album;
	
	public Song(Song other) {
		this(other.id, other.name, other.featuredArtists, other.album);
	}
	
	public Song(String Id, String name, List<Artist> featuredArtists, Album album) {
		this.id = Id;
		this.name = name;
		this.featuredArtists = featuredArtists;
		if(this.featuredArtists == null) {
			this.featuredArtists = new ArrayList<Artist>();
		}
		this.album = album;
	}
	
	public Album getAlbum() {
		return album;
	}

	public List<Artist> getFeaturedArtists() {
		return featuredArtists;
	}
	
	public void play() {
		System.out.println("Current Song Playing");
		System.out.println("Song - " + name);
		System.out.println("Album - " + ((album == null) ? "null" : album.getName()));
		StringBuilder sb = new StringBuilder();
		for(Artist art: featuredArtists) {
			if(sb.toString().isEmpty()) {
				sb.append(art.getName());
			} else {
				sb.append(",");
				sb.append(art.getName());
			}
		}
		System.out.println("Artists - " + sb.toString());
	}

	@Override
	public int hashCode() {
		return Objects.hash(featuredArtists, album);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Song)) {
			return false;
		}
		Song other = (Song) obj;
		return Objects.equals(featuredArtists, other.featuredArtists) && Objects.equals(album, other.album);
	}

	@Override
	public String toString() {
		return "Song [featuredArtists=" + featuredArtists + ", album=" + album + ", id=" + id + ", name=" + name + "]";
	}

}
