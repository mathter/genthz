package org.genthz.context;

/**
 * Stage of the object creation process.
 */
public enum Stage {
    /**
     * New context. Selected class instance do not exists.
     */
    NEW,

    /**
     * Class instance creating stage. ConstructorContext of the class is called usually.
     */
    INSTANCE_CREATING,

    /**
     * Class instance were created. ConstructorContext of the class were completed.
     */
    INSTANCE_CREATED,

    /**
     * Stage of object fields filling. For primitive classes or boxing types this stage can be skipped.
     */
    FILLING,

    /**
     * Object instance created and fields filled.
     */
    COMPLETE
}
