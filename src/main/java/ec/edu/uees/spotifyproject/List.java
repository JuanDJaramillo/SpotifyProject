/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.uees.spotifyproject;

/**
 *
 * @author Eduardo Yaguar
 */
public interface List <E> extends Iterable<E>{
    boolean addLast(E element);
    boolean addFirst(E element);
    boolean add(int index, E element);
    boolean isEmpty();
    int size();
    E get(int index);
    E removeFirst();
    E removeLast();
    E remove(int index);
    boolean contains(E element);
    int indexOf(E element);
    List<E> join(List<E> l);
    List<E> slicing(int start, int end);
}
