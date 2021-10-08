package com.todo.service;

import java.util.*;
import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	public static void createItem(TodoList l) {
		String title, desc, category, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 추가]\n"
				+ "제목 입력 -> ");
		title = sc.next();
		if (l.isDuplicate(title)) {
			System.out.println("제목이 중복됩니다!");
			System.out.println("제목을 수정해주세요.");
			return;
		}
		
		sc.nextLine();
		
		System.out.print("카테고리 입력 -> ");
		category = sc.next();
		
		sc.nextLine();
		
		System.out.print("내용 입력 -> ");
		desc = sc.nextLine().trim();
		
		System.out.print("마감일자 입력 (yyyy/mm/dd) -> ");
		due_date = sc.nextLine().trim();
		
		TodoItem t = new TodoItem(title, category, desc, due_date);
		if(l.addItem(t)>0)
			System.out.println("[항목 추가 완료]");
	}

	public static void deleteItem(TodoList l) {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n"
				+ "삭제할 항목의 번호 입력 -> ");
		int index = sc.nextInt();
		
		if (l.deleteItem(index)>0)
			System.out.println("[항목 삭제 완료]");
	}


	public static void updateItem(TodoList l) {
		String new_title, new_desc, new_category, new_due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 삭제]\n"
				+ "수정할 항목의 번호 입력 -> ");
		int index = sc.nextInt();  

		System.out.print("새 제목 입력 -> ");
		new_title = sc.next().trim();
		
		System.out.print("새 카테고리 입력 -> ");
		new_category = sc.next();
		
		sc.nextLine();
		
		System.out.print("새 내용 입력 -> ");
		new_desc = sc.nextLine().trim();
		
		System.out.print("새 마감일자 입력 (yyyy/mm/dd) -> ");
		new_due_date = sc.next().trim();
		
		TodoItem t = new TodoItem(new_title, new_desc, new_category, new_due_date);
		t.setId(index);
		if(l.updateItem(t)>0) 
			System.out.println("[항목 수정 완료]");
	}
	
	public static void findList(TodoList l, String keyword) {
		int count = 0;
		for (TodoItem item : l.getList(keyword)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("총 %d개의 항목을 찾았습니다.\n", count);
	}

	public static void listAll(TodoList l) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		
		for (TodoItem item : l.getList()) {
			System.out.println(item.toString());
		}
	}
	
	public static void listAll(TodoList l, String orderby, int ordering) {
		System.out.printf("[전체 목록, 총 %d개]\n", l.getCount());
		
		for (TodoItem item : l.getOrderedList(orderby, ordering)) {
			System.out.println(item.toString());
		}
	}
	
	public static void findItem(TodoList l) {
		int count = 0;
		int num = 1;
		String find;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.print("[항목 검색]\n"
				+ "찾을 항목의 키워드 입력 -> ");
		find = sc.next();  
		
		for (TodoItem item : l.getList()) {
			if (item.getTitle().contains(find) || item.getDesc().contains(find)) {
				System.out.print(num + ".");
				num ++;
				count ++;
				System.out.println(item.toString());
			}
		}
		System.out.println("\n총 "+count+"개의 항목이 검색됨");
	}
	
	public static void findCateList(TodoList l, String cate) {
		int count = 0;
		
		for (TodoItem item : l.getListCategory(cate)) {
			System.out.println(item.toString());
			count++;
		}
		System.out.printf("\n총 %d개의 항목을 찾았습니다.\n", count);
	}
	
	public static void listCateAll(TodoList l) {
		int count = 0;
		
		for(String item : l.getCategories()) {
			System.out.print(item + " ");
			count++;
		}
		System.out.printf("\n총 %d개의 카테고리가 등록되어 있습니다.\n", count);
	}
}