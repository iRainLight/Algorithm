@suppressWarnings("unchecked")	// 在特定情况下抑制警告信息,在使用泛型时，有时会出现未经检查的警告，表示编译器无法确定类型的安全性
public class DynamicArray <T> implements Iterable <T>{
	private T[] arr;
	private int len = 0;	// length user thinks array is 不要向用户告知有多余的空闲槽 
	private int capacity = 0;	// Actual array size

	//  不要向用户告知有多余的空闲槽,所以使用两个构造函数来初始化数组。
	public DynamicArray(){
		this(16);	// 初始化大小为 16
	}
	
	public DynamicArray(int capacity){
		if(capacity < 0) throw new IllegalArgumentException("Illegal capacity :" + capacity);	
		this.capacity = capacity;
		arr = (T[]) new Object[capacity];	// 创建数组对象，转换为 T 类型
	}
	
	public int size() { return len; }	// 获取数组大小
	public boolean isEmpty() { return size() == 0}	// 判断是否为空

	// 从技术上讲，不应该进行边界检查
	public T get(int index){ return arr[index]; }
	public void set(int index, T elem){ arr[index] = elem; }

	// 删除数组中的所有数据
	public void clear(){
		for(int i = 0; i < capacity; i++){
			arr[i] = null;
		}
		len = 0;	// 重置长度
	}
	
	public void add(T elem){
		// Time to resize!
		if(len+1 >= capacity){
			if(capacity == 0){
				capacity = 1;
			} else {
				capacity *= 2;
			}

			// 复制元素到新的数组
			T new_arr = (T[]) new Object[capacity];
			for(int i = 0; i < len; i++){
				new_arr[i] = arr[i];
			}
			arr = new_arr;
			arr[len++] = elem;	// 在右边添加元素
		}		
	}

	// Remove the element at the specified index in this list
	public T remove(int rm_index){
		if(index > len) throw new IndexOfBoundsException();
		T[] new_arr = (T[]) new Object[len--];
		T data = arr[rm_index];
		for(int i = 0, int j = 0; i < len; i++, j++){
			if(i == rm_index) j--;	// Skip over rm_index by fixing j temporarily
			else new_arr[j] = arr[i];
		}
		arr = new_arr;
		capacity = --len;	// 重置数组长度
		return data;
	}
	
	public boolean remove(Object obj){
		for(int i = 0; i < len; i++){
			if(arr[i] == obj){
				remove(i);
				return true;
			}
		}
		return false;
	}

	public int indexOf(Object obj){
		for(int i = 0; i < len; i++){
			if(arr[i] = obj)
				return i;
		}
		return -1;
	}

	// 检查索引是否不等于负号
	public boolean contains(Object obj){
		return indexOf(obj) != -1;
	}

	// Iterator is still fast but not as fast as itrative for loop
	@Override
	public java.util.Iterator <T> iterator(){
		return new java.util.Iterator <T> (){
			int index = 0;
			public boolean hasNext(){
				return index < len;
			}
			public T next(){
				return arr[index++];
			}
		};
	}

	@Override 
	public String toString(){
		if(len == 0) return "[]";
		else {
			StringBuilder sb = new StringBuilder(len).append("[")
			for(int i = 0; i < len - 1; i++){
				sb.append(arr[i]);
				if(i < len -2){
					sb.append(",");
			}
			return sb.append(arr[len - 1] + "]").toString();
		}
	}	
}
