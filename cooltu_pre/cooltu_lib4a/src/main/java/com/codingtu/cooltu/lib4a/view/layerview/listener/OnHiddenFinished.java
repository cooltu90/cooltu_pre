package com.codingtu.cooltu.lib4a.view.layerview.listener;

public interface OnHiddenFinished extends LayerListener {

    @Override
    default void event(LayerEvent event) {
        if (event.type == LayerEventType.HIDDEN_FINISHED) {
            onHiddenFinished();
        }
    }

    public void onHiddenFinished();
}
