//package vip.ngtv.frameworks.persistence.spring;
//
//import vip.ngtv.frameworks.domain.core.Page;
//
//import java.util.List;
//import java.util.function.Function;
//
//public class SpringPage<T> implements Page<T> {
//    private final org.springframework.data.domain.Page<T> impl;
//
//    protected SpringPage(org.springframework.data.domain.Page<T> impl) {
//        this.impl = impl;
//    }
//
//    public static <T> SpringPage<T> from(org.springframework.data.domain.Page<T> page) {
//        return new SpringPage<>(page);
//    }
//
//    public List<T> getContent() {
//        return impl.getContent();
//    }
//
//    public long getTotalElements() {
//        return impl.getTotalElements();
//    }
//
//    public int getSize() {
//        return impl.getSize();
//    }
//
//    public int getNumber() {
//        return impl.getNumber();
//    }
//
//    public int getNumberOfElements() {
//        return impl.getNumberOfElements();
//    }
//
//    @Override
//    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
//        return new SpringPage<>(impl.map(converter));
//    }
//}
