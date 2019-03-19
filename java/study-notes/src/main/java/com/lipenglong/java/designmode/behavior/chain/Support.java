package com.lipenglong.java.designmode.behavior.chain;

/**
 * study-notes
 * <p/>
 *
 * @author petric
 * @since 1.0-SNAPSHOT
 */
public abstract class Support {
    private String name;
    private Support next;

    Support(String name) {
        this.name = name;
    }

    Support setNext(Support next) {
        this.next = next;
        return next;
    }

    final void support(Trouble trouble) {
        if (resolve(trouble)) {
            done(trouble);
        } else if (next != null) {
            next.support(trouble);
        } else {
            fail(trouble);
        }
    }

    private void fail(Trouble trouble) {
        System.out.println("trouble" + trouble.getNumber() +
                " can not be resolved! " + this.getClass().getSimpleName());
    }

    private void done(Trouble trouble) {
        System.out.println("trouble" + trouble.getNumber() +
                " is resolved by " + this.getClass().getSimpleName() + ".");
    }

    protected abstract boolean resolve(Trouble trouble);
}
