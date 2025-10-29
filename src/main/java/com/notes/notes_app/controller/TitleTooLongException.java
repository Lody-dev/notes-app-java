package com.notes.notes_app.controller;

    public class TitleTooLongException extends RuntimeException {
        public TitleTooLongException(String message) {
            super(message);
        }
}
