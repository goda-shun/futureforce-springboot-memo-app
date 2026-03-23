package com.lesson.memo.model;

public enum Priority {
	high(1),
	medium(2),
	low(3);

	private final int level;

    // コンストラクタ
    Priority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }
    
}

