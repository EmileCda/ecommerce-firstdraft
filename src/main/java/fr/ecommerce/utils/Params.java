package fr.ecommerce.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;


@Entity
@Table(name = "params")
public class Params implements Serializable {

	private static final long serialVersionUID = 1L;
	private static final int  DEFAULT_ID= -1;
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name = "function_code",nullable = false, unique = true)
	private int functionCode;
	@Column(nullable = false)
	private byte[] blobKey;
	@Column(nullable = false)
	private int codeLentgh;
	@Column(nullable = false)
	private String algorythm;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModification;

	public Params() {
		this(DEFAULT_ID,-1,null,0,"",null);
	}
	public Params(int functionCode, byte[] blobKey, int codeLentgh, String algorythm) {
		
		
		this(DEFAULT_ID,functionCode,blobKey,codeLentgh,algorythm,null);
		
		
	}
	public Params(int id, int functionCode, byte[] blobKey, int codeLentgh, String algorythm, Date lastModification) {
		this.setId(id);
		this.setFunctionCode(functionCode);
		this.setBlobKey(blobKey);
		this.setCodeLentgh(codeLentgh);
		this.setAlgorythm(algorythm);
		this.setLastModification(lastModification);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(int functionCode) {
		this.functionCode = functionCode;
	}

	public byte[] getBlobKey() {
		return blobKey;
	}

	public void setBlobKey(byte[] blobKey) {
		this.blobKey = blobKey;
	}

	public int getCodeLentgh() {
		return codeLentgh;
	}

	public void setCodeLentgh(int codeLentgh) {
		this.codeLentgh = codeLentgh;
	}

	public String getAlgorythm() {
		return algorythm;
	}

	public void setAlgorythm(String algorythm) {
		this.algorythm = algorythm;
	}

	public Date getLastModification() {
		return lastModification;
	}

	public void setLastModification(Date lastModification) {
		this.lastModification = lastModification;
	}
	@Override
	public String toString() {
		return String.format(
				"Id[%d], Codo=%s, BlobKey()=%s, getCodeLentgh()=%s, getAlgorythm()=%s, getLastModification()=%s]",
				getId(), getFunctionCode(), Arrays.toString(getBlobKey()), getCodeLentgh(), getAlgorythm(),
				getLastModification());
	}

}
