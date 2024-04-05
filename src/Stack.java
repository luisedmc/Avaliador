/*
 * Luis Eduardo Meduna C. - 10408871
 */

public class Stack {
  private char data[];
  private int nums[];
  private int top;
  private int count;
  private int capacity;

  Stack(int size) {
    data = new char[size];
    nums = new int[size];
    top = -1;
    count = 0;
    capacity = size;
  }

  public void Push(char c) {
    if (isFull()) {
      System.out.println("Stack full!");
      return;
    }
    data[++top] = c;
    count++;
  }

  public void PushInt(int n) {
    if (isFull()) {
      System.out.println("Stack full!");
      return;
    }
    nums[++top] = n;
    count++;
  }

  public char Pop() {
    if (isEmpty())
      return '\0';

    count--;
    return data[top--];
  }

  public int PopInt() {
    if (isEmpty())
      return 0;

    count--;
    return nums[top--];
  }

  public char Peek() {
    if (isEmpty())
      return '\0';

    return data[top];
  }

  public void Print() {
    System.out.println("Stack: ");
    System.out.println("Top: " + top + " Count: " + count + " Capacity: " + capacity);
    for (int i = 0; i < count; i++)
      System.out.print(data[i]);

    System.out.println();
  }

  public Boolean isFull() {
    return count == capacity;
  }

  public Boolean isEmpty() {
    return count == 0;
  }
}
