package com.mysite.sbb.question;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Sort;
@Service
@RequiredArgsConstructor
public class QuestionService {
	public final QuestionRepository questionRepository;

	public Page<Question> getList(int page) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.questionRepository.findAll(pageable);
	}
	public Question getQuestion(Integer id){
		Optional<Question> op = questionRepository.findById(id);
		if(op.isEmpty())
			throw new DataNotFoundException("question not found");
		return op.get();
	}
	public void create(String subject, String content){
		Question q = new Question();
		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		questionRepository.save(q);
	}
}
