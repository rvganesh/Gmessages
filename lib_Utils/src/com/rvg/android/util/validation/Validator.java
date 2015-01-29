package com.rvg.android.util.validation;

/**
 * Abstract class that all the validators need to extend.
 */
public abstract class Validator {
    private Validators mValidators;

    public void setValidators(Validators validators) {
        mValidators = validators;
    }

    public abstract boolean isValid();

    public Validators getValidators() {
        return mValidators;
    }
}