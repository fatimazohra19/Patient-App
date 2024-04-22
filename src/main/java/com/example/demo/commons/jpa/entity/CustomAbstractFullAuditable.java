package com.example.demo.commons.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;


@MappedSuperclass
@SuperBuilder
@Getter @Setter
@RequiredArgsConstructor @AllArgsConstructor
public abstract class CustomAbstractFullAuditable<PK extends Serializable> extends CustomAbstractAuditable<PK>	  {




	@LastModifiedDate
	private LocalDateTime modifiedDate;


	@LastModifiedBy
	private String modifiedBy;

}
