/**
 * Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.krminc.phr.domain.carenotebook;

import java.util.List;
import java.util.Vector;

/**
 * @author cmccall
 */
public class FamilyMembers {
	private Vector<FamilyMember> members;
	private Long healthRecordId;

	public Long getHealthRecordId() {
		return healthRecordId;
	}

	public FamilyMembers(Vector<FamilyMember> members, Long healthRecordId) {
		if (members.isEmpty()) {
			members.add(new FamilyMember(healthRecordId));
		}

		this.members = members;
		this.healthRecordId = healthRecordId;
	}

	public List<FamilyMember> getMembers() {
		return members;
	}

	public void setMembers(Vector<FamilyMember> members) {
		this.members = members;
	}

	private String getNameByContactType(String specialtyString) {
		for (FamilyMember member : members) {
			if (member.getFamilymemberType().equalsIgnoreCase(specialtyString)) {
				return member.getFamilymemberName();
			}
		}
		return "";
	}

	private String getNumberByContactType(String specialtyString) {
		for (FamilyMember member : members) {
			if (member.getFamilymemberType().equalsIgnoreCase(specialtyString)) {
				return member.getDaytimePhoneNumber();
			}
		}
		return "";
	}

	public String getFatherName() {
		return getNameByContactType("Father");
	}

	public String getFatherNumber() {
		return getNumberByContactType("Father");
	}

	public String getMotherName() {
		return getNameByContactType("Mother");
	}

	public String getMotherNumber() {
		return getNumberByContactType("Mother");
	}

	public String getEmergencyContactName() {
		for (FamilyMember member : members) {
			if (member.getIsPrimary()) {
				return member.getFamilymemberName();
			}
		}
		return "";
	}

	public String getEmergencyContactRelationship() {
		for (FamilyMember member : members) {
			if (member.getIsPrimary()) {
				return member.getFamilymemberType();
			}
		}
		return "";
	}

	public String getEmergencyContactDaytimeNumber() {
		for (FamilyMember member : members) {
			if (member.getIsPrimary()) {
				return member.getDaytimePhoneNumber();
			}
		}
		return "";
	}

	public String getEmergencyContactEveningNumber() {
		for (FamilyMember member : members) {
			if (member.getIsPrimary()) {
				return member.getEveningPhoneNumber();
			}
		}
		return "";
	}
}
