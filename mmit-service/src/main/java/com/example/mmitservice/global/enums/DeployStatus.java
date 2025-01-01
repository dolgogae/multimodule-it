package com.example.mmitservice.global.enums;

public enum DeployStatus {
    STOPPED {
        @Override
        public void execute() {
            stopDeploy();
        }
    },
    NOT_IN_PROGRESS {
        @Override
        public void execute() {
            stopDeploy();
        }
    },
    COMPLETED {
        @Override
        public void execute() {
            stopDeploy();
        }
    },
    IN_PROGRESS {
        @Override
        public void execute() {
            startDeploy();
        }
    };

    public abstract void execute();
    private static void stopDeploy(){
        System.out.println("stop deploy");
    }
    private static void startDeploy(){
        System.out.println("start deploy");
    }
}
