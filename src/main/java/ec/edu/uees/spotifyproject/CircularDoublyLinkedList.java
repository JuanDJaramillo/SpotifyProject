/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

import java.util.Iterator;
import java.util.ListIterator;


/**
 *
 * @author Eduardo Yaguar
 */
public class CircularDoublyLinkedList<E> implements List<E> {
    
    private Node<E> last;
    private int current;
    
    class Node<E>{
        E data;
        Node<E> next;
        Node<E> previous;
        
        Node(E data){
            this.data = data;
        }  
    }
    
    @Override
    public boolean addLast(E element) {
        if (element == null)return false;
        Node<E> p = new Node<>(element);
        if (this.isEmpty()){ 
            this.last = p;
            this.last.previous = p;
            this.last.next = p;
        }else{
            p.next = this.last.next;
            p.previous = this.last;
            this.last.next = p;
            this.last = p;
        }
        this.current++;
        return true;
    }

    @Override
    public boolean addFirst(E element) {
        if (element == null)return false;
        Node<E> p = new Node<>(element);
        if (this.isEmpty()){ 
            this.last = p;
            this.last.previous = p;
            this.last.next = p;
        }else{
            p.next = this.last.next;
            p.previous = this.last;
            this.last.next.previous = p;
            this.last.next = p;
        }
        this.current++;
        return true;
    }

    @Override
    public boolean add(int index, E element) {
        if(index < 0 || index > this.current || element == null ) return false;
        if(index == 0) return addFirst(element);
        if (index == this.current) return addLast(element);
        
        Node<E> n = new Node<>(element);
        Node <E> p = this.last.next;
        int i = 0;
        
        while(i < index-1){
            p = p.next;
            i++;
        }
        
        n.next = p.next;
        n.previous = p;
        p.next.previous = n;
        p.next = n;
        
        this.current++;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return this.last == null;
    }

    @Override
    public int size() {
        return this.current;
    }

    @Override
    public E get(int index) {
        int i = 0;
        
        if (index>=0 && index<this.current){
            Node<E> p = this.last.next;
            while(index != i){
                p = p.next;
                i++;
            }
            return p.data;
        }else{
            return null;
        }
    }

    @Override
    public E removeFirst() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E removeLast() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean contains(E element) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int indexOf(E element) {        
        int i = 0;
        Node<E> p = this.last.next;
        do{
           if(p.data.equals(element)) return i;
           p = p.next;
           i++;
        }while(p != this.last.next);
        
        throw new RuntimeException("El elemento no se encuentra en la lista");        
    }

    @Override
    public List<E> join(List<E> l) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<E> slicing(int start, int end) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Iterator<E> iterator() {
        Iterator<E> it = new Iterator<E>(){
            Node<E> p = last.next;
            
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public E next() {
                E tmp = p.data;
                p = p.next;
                return tmp;
            }
        };
        return it;
    }
    
    public String toString(){
        
        if(this.isEmpty()) return "[]";
        
        StringBuilder s = new StringBuilder();
        s.append("[");
        
        Node<E> p = this.last.next;
        do{
            s.append(p.data);
            if (p != this.last) s.append(",");
            p = p.next;
        }while(p != this.last.next);
        
        s.append("]");
        
        return s.toString();
    }
    
    
    public ListIterator<E> listIterator(){
        ListIterator<E> lit = new ListIterator<E>(){
            Node<E> p = last.next;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public E next() {
                E tmp = p.data;
                p = p.next;
                return tmp;
            }

            @Override
            public boolean hasPrevious() {
                return true;           
            }

            @Override
            public E previous() {
                E tmp = p.data;
                p = p.previous;
                return tmp;            
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        return lit;
    }
    
    public ListIterator<E> listIterator(int i) {
        ListIterator<E> lit = new ListIterator<E>() {
            Node<E> p = getNodeAtIndex(i);

            private Node<E> getNodeAtIndex(int index) {
                if (index < 0 || index >= current) {
                    throw new IndexOutOfBoundsException("El índice está fuera de rango.");
                }
                
                int cont = 0;
                Node<E> p = last.next;
                while(cont < index){
                    p = p.next;
                    cont++;
                }
                
                return p;
            }

            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public E next() {
                if (i == current-1) {
                    p = last.next;
                } else {
                    p = p.next;
                }
                return p.data;   
            }

            @Override
            public boolean hasPrevious() {
                return true;           
            }

            @Override
            public E previous() {
                if (i == 0) {
                    p = last;
                } else {
                    p = p.previous;
                }
                return p.data;            
            }

            @Override
            public int nextIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public int previousIndex() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

                @Override
            public void remove() {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        };
        
        return lit;
    }
    
}
