package gofishfinal.players;

import gofishfinal.ui.Card;
import java.util.ArrayList;

public class HeapSort {

    private Card[] a;
    private int n;
    private int left;
    private int right;
    private int largest;

    public HeapSort() {

    }

    public HeapSort(Card[] a) {
        sort(a);
    }

    public HeapSort(Player player) {
        process(player);
    }

    public void process(Player player) {
        Card[] card = new Card[player.getHand().size()];
        Object[] temp = player.getHand().toArray();
        for (int i = 0; i < card.length; i++) {
            card[i] = (Card) temp[i];
            if (card[i].getRank().equals("10")) {
                card[i].setRank(":");
            }
        }
        a = card;
        sort(a);

        player.getHand().clear();
        for (Card a1 : a) {
            if (a1.getRank().equals(":")) {
                a1.setRank("10");
            }
            player.getHand().add(a1);
        }
    }

    public void process(ArrayList<Card> cards) {
        Card[] card = new Card[cards.size()];
        Object[] temp = cards.toArray();
        for (int i = 0; i < card.length; i++) {
            card[i] = (Card) temp[i];
            if (card[i].getRank().equals("10")) {
                card[i].setRank(":");
            }
        }
        a = card;
        sort(a);

        cards.clear();
        for (Card a1 : a) {
            if (a1.getRank().equals(":")) {
                a1.setRank("10");
            }
            cards.add(a1);
        }
    }

    public void buildheap(Card[] a) {

        n = a.length - 1;
        for (int i = n / 2; i >= 0; i--) {
            maxheap(a, i);
        }
    }

    public void maxheap(Card[] a, int i) {
        left = 2 * i;
        right = 2 * i + 1;

        if (left <= n && a[i].getRank().compareTo(a[left].getRank()) < 0) {
            largest = left;
        } else {
            largest = i;
        }

        if (right <= n && a[right].getRank().compareTo(a[largest].getRank()) > 0) {
            largest = right;
        }
        if (largest != i) {
            swap(i, largest);
            maxheap(a, largest);
        }
    }

    public void swap(int i, int j) {
        Card t = a[i];
        a[i] = a[j];
        a[j] = t;

    }

    public void sort(Card[] a0) {

        a = a0;
        buildheap(a);

        for (int i = n; i > 0; i--) {
            swap(0, i);
            n = n - 1;
            maxheap(a, 0);
        }
    }

    public void sort(Player player) {
        process(player);
    }

    public void sort(ArrayList<Card> a) {
        process(a);
    }
}
