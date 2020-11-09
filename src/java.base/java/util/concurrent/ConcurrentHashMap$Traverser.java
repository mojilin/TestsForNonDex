/*
 * Decompiled with CFR 0.150.
 * 
 * Could not load the following classes:
 *  edu.illinois.nondex.shuffling.ControlNondeterminism
 */
package java.util.concurrent;

import edu.illinois.nondex.shuffling.ControlNondeterminism;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

class ConcurrentHashMap$Traverser<K, V> {
    ConcurrentHashMap.Node<K, V>[] tab;
    ConcurrentHashMap.Node<K, V> next;
    ConcurrentHashMap.TableStack<K, V> stack;
    ConcurrentHashMap.TableStack<K, V> spare;
    int index;
    int baseIndex;
    int baseLimit;
    final int baseSize;

    ConcurrentHashMap$Traverser(ConcurrentHashMap.Node<K, V>[] tab, int size, int index, int limit) {
        this.tab = tab;
        this.baseSize = size;
        this.baseIndex = this.index = index;
        this.baseLimit = limit;
        this.next = null;
        if (tab != null) {
            this.tab = (ConcurrentHashMap.Node[])ControlNondeterminism.shuffle((Object[])Arrays.copyOf(tab, tab.length));
        }
    }

    final ConcurrentHashMap.Node<K, V> advance() {
        ConcurrentHashMap.Node<K, V> e = this.next;
        if (e != null) {
            e = e.next;
        }
        while (true) {
            int i;
            int n;
            ConcurrentHashMap.Node<K, V>[] t;
            block10: {
                block9: {
                    if (e != null) {
                        this.next = e;
                        return this.next;
                    }
                    if (this.baseIndex >= this.baseLimit) break block9;
                    t = this.tab;
                    if (this.tab != null && (n = t.length) > (i = this.index) && i >= 0) break block10;
                }
                this.next = null;
                return null;
            }
            e = ConcurrentHashMap.tabAt(t, i);
            if (e != null && e.hash < 0) {
                if (e instanceof ConcurrentHashMap.ForwardingNode) {
                    this.tab = ((ConcurrentHashMap.ForwardingNode)e).nextTable;
                    e = null;
                    this.pushState(t, i, n);
                    continue;
                }
                e = e instanceof ConcurrentHashMap.TreeBin ? ((ConcurrentHashMap.TreeBin)e).first : null;
            }
            if (this.stack != null) {
                this.recoverState(n);
                continue;
            }
            this.index = i + this.baseSize;
            if (this.index < n) continue;
            this.index = ++this.baseIndex;
        }
    }

    private void pushState(ConcurrentHashMap.Node<K, V>[] t, int i, int n) {
        ConcurrentHashMap.TableStack<K, V> s = this.spare;
        if (s != null) {
            this.spare = s.next;
        } else {
            s = new ConcurrentHashMap.TableStack();
        }
        s.tab = t;
        s.length = n;
        s.index = i;
        s.next = this.stack;
        this.stack = s;
    }

    private void recoverState(int n) {
        int len;
        ConcurrentHashMap.TableStack<K, V> s;
        while ((s = this.stack) != null && (this.index += (len = s.length)) >= n) {
            n = len;
            this.index = s.index;
            this.tab = s.tab;
            s.tab = null;
            ConcurrentHashMap.TableStack next = s.next;
            s.next = this.spare;
            this.stack = next;
            this.spare = s;
        }
        if (s == null && (this.index += this.baseSize) >= n) {
            this.index = ++this.baseIndex;
        }
    }
}