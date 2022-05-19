package pojos;

class finalClassTest extends Samplettestclass {
    @Override
    public void inc2() {
        inc1();
    }

    @Override
    public void inc1() {
        x = 20;
    }
}

