package com.example.natour21.Entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InterestingPoint implements Serializable, Parcelable {

	//Class Primary Key
	@Expose
	@SerializedName("id_interestingpoint")
	private Long id_interestingpoint;

	//Class Foreign Key
	@Expose
	@SerializedName("id_itinerario")
	private Long id_itinerario;

	//Campi Locali
	@Expose
	@SerializedName("latitudine")
	private Double latitudine;
	@Expose
	@SerializedName("longitudine")
	private Double longitudine;
	@Expose
	@SerializedName("titolo")
	private String titolo;
	@Expose
	@SerializedName("descrizione")
	private String descrizione;
	@Expose
	@SerializedName("urlfoto")
	private String urlfoto;

	/*********************************************************************************************/

	//Constructor
	public InterestingPoint(Long id_interestingpoint, Long id_itinerario, Double latitudine, Double longitudine,
							String titolo, String descrizione, String urlfoto) {
		super();
		this.id_interestingpoint = id_interestingpoint;
		this.id_itinerario = id_itinerario;
		this.latitudine = latitudine;
		this.longitudine = longitudine;
		this.descrizione = descrizione;
		this.titolo = titolo;
		this.urlfoto = urlfoto;
	}

	public InterestingPoint() {}

	//Parcelable
	protected InterestingPoint(Parcel in) {
		if (in.readByte() == 0) {
			id_interestingpoint = null;
		} else {
			id_interestingpoint = in.readLong();
		}
		if (in.readByte() == 0) {
			id_itinerario = null;
		} else {
			id_itinerario = in.readLong();
		}
		if (in.readByte() == 0) {
			latitudine = null;
		} else {
			latitudine = in.readDouble();
		}
		if (in.readByte() == 0) {
			longitudine = null;
		} else {
			longitudine = in.readDouble();
		}
		titolo = in.readString();
		descrizione = in.readString();
		urlfoto = in.readString();
	}

	public static final Creator<InterestingPoint> CREATOR = new Creator<InterestingPoint>() {
		@Override
		public InterestingPoint createFromParcel(Parcel in) {
			return new InterestingPoint(in);
		}

		@Override
		public InterestingPoint[] newArray(int size) {
			return new InterestingPoint[size];
		}
	};

	@Override
	public String toString() {
		return "InterestingPoint{" +
				"id_interestingpoint=" + id_interestingpoint +
				", id_itinerario=" + id_itinerario +
				", latitudine=" + latitudine +
				", longitudine=" + longitudine +
				", titolo='" + titolo + '\'' +
				", descrizione='" + descrizione + '\'' +
				", urlfoto='" + urlfoto + '\'' +
				'}';
	}

	/*********************************************************************************************/

	//Getter e Setter
	public Long getId_interestingpoint() {
		return id_interestingpoint;
	}


	public void setId_interestingpoint(Long id_interestingpoint) {
		this.id_interestingpoint = id_interestingpoint;
	}


	public Long getId_itinerario() {
		return id_itinerario;
	}


	public void setId_itinerario(Long id_itinerario) {
		this.id_itinerario = id_itinerario;
	}


	public Double getLatitudine() {
		return latitudine;
	}


	public void setLatitudine(Double latitudine) {
		this.latitudine = latitudine;
	}


	public Double getLongitudine() {
		return longitudine;
	}


	public void setLongitudine(Double longitudine) {
		this.longitudine = longitudine;
	}


	public String getTitolo() {
		return titolo;
	}


	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	
	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	public String getUrlfoto() {
		return urlfoto;
	}


	public void setUrlfoto(String urlfoto) {
		this.urlfoto = urlfoto;
	}


	//For parcelable
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		if (id_interestingpoint == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeLong(id_interestingpoint);
		}
		if (id_itinerario == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeLong(id_itinerario);
		}
		if (latitudine == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeDouble(latitudine);
		}
		if (longitudine == null) {
			dest.writeByte((byte) 0);
		} else {
			dest.writeByte((byte) 1);
			dest.writeDouble(longitudine);
		}
		dest.writeString(titolo);
		dest.writeString(descrizione);
		dest.writeString(urlfoto);
	}

	/*********************************************************************************************/

}
