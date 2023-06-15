package com.codingbox.sprip.member.validation;

import javax.validation.GroupSequence;

import com.codingbox.sprip.member.validation.ValidationGroup.EmailCheckGroup;
import com.codingbox.sprip.member.validation.ValidationGroup.NotNullGroup;
import com.codingbox.sprip.member.validation.ValidationGroup.PatternCheckGroup;
import com.codingbox.sprip.member.validation.ValidationGroup.SizeCheckGroup;

@GroupSequence({NotNullGroup.class, SizeCheckGroup.class,
				PatternCheckGroup.class, EmailCheckGroup.class})

public interface ValidationSequence {

}
