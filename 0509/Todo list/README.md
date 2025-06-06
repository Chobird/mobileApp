# Android Todo List 앱

## 프로젝트 개요
이 프로젝트는 Android Studio를 사용하여 개발된 할 일 관리 애플리케이션입니다. 사용자가 일상적인 할 일을 효율적으로 관리할 수 있도록 도와주는 앱입니다.

## SWOT 분석

### 강점 (Strengths)
- 직관적인 사용자 인터페이스
- 우선순위 설정 기능으로 중요도 관리 가능
- 카테고리 분류로 할 일 체계적 관리
- 한글 지원으로 한국 사용자 친화적
- 간단한 수정/삭제 기능

### 약점 (Weaknesses)
- 데이터 영구 저장 기능 부재
- 알림 기능 미구현
- 할 일 정렬 기능 부재
- 검색 기능 부재
- 다크 모드 미지원

### 기회 (Opportunities)
- 클라우드 동기화 기능 추가 가능
- 알림 및 리마인더 기능 확장
- 통계 및 분석 기능 추가
- 위젯 기능 구현
- 다국어 지원 확장

### 위협 (Threats)
- 유사한 기능의 앱들과의 경쟁
- 사용자 데이터 보안 이슈
- Android 버전 호환성 문제
- 사용자 요구사항 변화
- 앱 성능 최적화 필요성

## 주요 기능
1. 할 일 추가
   - 제목 입력
   - 우선순위 설정 (높음/중간/낮음)
   - 카테고리 선택

2. 할 일 관리
   - 수정 기능
   - 삭제 기능
   - 완료 표시

3. 카테고리 분류
   - 기본
   - 숙제
   - 약속
   - 루틴
   - 업무
   - 취미
   - 기타

## 기술 스택
- Java
- Android SDK
- Android Studio

## 프로젝트 구조
```
Todo list/
├── java/
│   └── com/
│       └── example/
│           └── todo2/
│               ├── MainActivity.java
│               ├── TodoAdapter.java
│               └── TodoItem.java
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── dialog_edit_todo.xml
    │   └── todo_item.xml
    └── values/
        └── arrays.xml
```

## 향후 개선 사항
1. 데이터베이스 연동
2. 알림 기능 추가
3. 검색 기능 구현
4. 정렬 기능 추가
5. 다크 모드 지원
6. 위젯 기능 구현
7. 클라우드 동기화
8. 통계 기능 추가 