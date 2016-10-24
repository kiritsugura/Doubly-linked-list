

import java.util.Iterator;
import java.util.NoSuchElementException;
public class LinkedList<E>{
    protected Node mBegin,mEnd;
    protected int mSize;
    public LinkedList(){
        mSize=0;
        mBegin=null;
        mEnd=null;
    }  

    public void addToEnd(E obj){
        if(mSize==0){
            mSize++;
            mEnd=new Node(obj);  
            mBegin=mEnd;
        }else{
            mSize++;
            Node newNode=new Node(obj);
            mEnd.mNext=newNode;
            newNode.mPrevious=mEnd;
            mEnd=newNode;
            if(mSize==1){
                mEnd=mBegin;
            }else if(mSize==2){
                mEnd=mBegin.mNext;
            }
        }
    }

    public void addToBegin(E obj){
        if(mSize==0){
            mSize++;
            mBegin=new Node(obj);
            mEnd=mBegin;
        }else{
            mSize++;
            Node newNode=new Node(obj);
            mBegin.mPrevious=newNode;
            newNode.mNext=mBegin;
            mBegin=newNode;
            if(mSize==1){
                mBegin=mEnd;
            }else if(mSize==2){
                mEnd=mBegin.mNext;
            }
            
        }
    }

    @Override
    public String toString(){
        if(mSize==0)
            return"<empty>";
        else if(mSize==1)
            return "<["+mBegin.mValue+"]>";
        else{
            String listItems="<";
            Node loopingNode=mBegin;
            while(!(loopingNode==null)){
                listItems+="["+loopingNode.mValue+"]";
                loopingNode=loopingNode.mNext;
            }
            return(listItems+=">");
        }
    }

    public int removeAll(E toBeRemoved){
        int numOfRemovals=0;
        Node loopingNode=mBegin;
        while(!(loopingNode==null)){
            if(toBeRemoved.equals(loopingNode.mValue)){
                numOfRemovals++;
                mSize--;
                if(loopingNode.mNext==null){
                    loopingNode.mPrevious.mNext=null;
                    mEnd=loopingNode.mPrevious;
                }else if(loopingNode.mPrevious==null){
                    mBegin=loopingNode.mNext;
                    loopingNode.mNext.mPrevious=null; 
                }else{
                    loopingNode.mNext.mPrevious=loopingNode.mPrevious;
                    loopingNode.mPrevious.mNext=loopingNode.mNext;
                }
            }
            loopingNode=loopingNode.mNext;
        }
        return numOfRemovals;
    }

    public int count(E objToSearchFor){
        int numOfOccurances=0;
        Node loopingNode=mBegin;
        while(!(loopingNode==null)){
            if(objToSearchFor.equals(loopingNode.mValue))
                numOfOccurances++;
            loopingNode=loopingNode.mNext;
        }
        return numOfOccurances;
    }

    public LinkedListIterator iterator(){
        return new LinkedListIterator(mBegin);
    }

    public LinkedListIterator riterator(){
        return new LinkedListIterator(mEnd);
    }    

    public LinkedListIterator<E> getAt(int index,boolean isFromFront) throws IndexOutOfBoundsException{
        if(index<0||index>=mSize){
            throw new IndexOutOfBoundsException();
        }else if(isFromFront){
            Node loopingNode=mBegin;
            int currentIndex=0;
            while(!(loopingNode==null)){
                if(currentIndex==index){
                    return new LinkedListIterator<E>(loopingNode);
                }
                loopingNode=loopingNode.mNext;
                currentIndex++;
            }
        }else{
            Node loopingNode=mEnd;
            int currentIndex=0;
            while(!(loopingNode==null)){
                if(currentIndex==index){
                    return new LinkedListIterator<E>(loopingNode);
                }
                loopingNode=loopingNode.mPrevious;       
                currentIndex++;
            }                
        }
        return null;
    }

   public void addAfter(LinkedListIterator<E> iterator,E obj){
        Node newestNode=new Node(obj); 
        newestNode.mPrevious=iterator.currentNode;
        newestNode.mNext=iterator.currentNode.mNext;
        iterator.currentNode.mNext.mPrevious=newestNode;
        iterator.currentNode.mNext=newestNode;
        mSize++;
    }   
    public class LinkedListIterator<T extends E> implements Iterator<E>{
        protected Node currentNode,oldNode;
        protected boolean isReverse;
        protected int currentSizeNext,currentSizePrevious;
        public LinkedListIterator(Node current){
            currentNode=current;
            currentSizePrevious=0;
            currentSizeNext=0;
            if(current.mNext==null)
                isReverse=true;
            else if(current.mPrevious==null)
                isReverse=false;
        }
        //tested
        @Override
        public boolean hasNext(){ 
            return currentSizeNext!=mSize;
        }
        //tested
        public boolean hasPrevious(){ 
            return currentSizePrevious!=mSize;
        } 
        //tested
        @Override
        public E next() throws NoSuchElementException{
            if(currentSizeNext>mSize){
                throw new NoSuchElementException();
            }else{
                if(currentSizePrevious==mSize){
                    currentNode=mBegin;
                    oldNode=null;
                }                
                oldNode=currentNode;
                currentNode=currentNode.mNext;
                currentSizeNext++;
                currentSizePrevious=mSize-currentSizeNext;                
                return oldNode.mValue;
            }
        }
        //tested
        public E previous() throws NoSuchElementException{
            if(currentSizePrevious>mSize){
                throw new NoSuchElementException();
            }else{
                if(currentSizeNext==mSize){
                    currentNode=mEnd;
                    oldNode=null;
                }
                oldNode=currentNode;
                currentNode=currentNode.mPrevious;
                currentSizePrevious++;
                currentSizeNext=mSize-currentSizePrevious;
                return oldNode.mValue;
            }
        }     
        //tested
        @Override
        public void remove() throws NoSuchElementException{
            if(mSize==0){
                throw new NoSuchElementException();
            }else if(mSize==1){
                mBegin=null;
                mEnd=null;
            }
            mEnd.mPrevious.mNext=null;
            mEnd=mEnd.mPrevious;
        }
    }
    protected class Node{    
        Node mNext,mPrevious;
        E mValue;
        Node(E obj){
            mValue=obj;
        }
        
    }
}
