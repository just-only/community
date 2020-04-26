create table comment
(
	id bigint not null,
	question_id bigint not null,
	type int default 1 not null,
	commentor_id bigint not null,
	create_time bigint not null,
	modefied bigint not null,
	like_count bigint not null,
	comment_txt text not null,
	constraint comment_pk
		primary key (id)
);