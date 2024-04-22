package com.example.demo.commons.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import com.example.demo.commons.jpa.entity.CustomAbstractPersistable;
import jakarta.persistence.*;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@SuperBuilder
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public abstract class CustomAbstractAuditable<PK extends Serializable> extends CustomAbstractPersistable<PK> {

	@Column(updatable = false)
	@CreatedDate
	private LocalDateTime createdDate;

	@Column(updatable = false)
	@CreatedBy
	private String createdBy;


//	@LastModifiedDate
//	private LocalDateTime modifiedDate;
//
//
//	@LastModifiedBy
//	private String modifiedBy;

}
