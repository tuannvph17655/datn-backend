package com.datn.utils.common;


import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.nimbusds.oauth2.sdk.util.CollectionUtils;
import lombok.experimental.UtilityClass;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@UtilityClass
public class ApiCollectionUtils {

    /**
     * Get the size of a collection.
     * @param collection can be null.
     * @return 0 if collection is null. Otherwise, return its size.
     */
    public static int sizeOf(@Nullable final Collection<?> collection) {
        return collection == null ? 0 : collection.size();
    }

    /**
     * Check if a collection is empty or not.
     * @param collection can be null.
     * @return true if collection is null or is empty. Otherwise, return false.
     */
    public static boolean isEmpty(@Nullable final Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * Check if a collection is not empty.
     * @param collection can be null.
     * @return true if collection has items. Otherwise, return false.
     */
    public static boolean isNotEmpty(@Nullable final Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static <T> List<T> emptyListIfNull(final @Nullable List<T> list) {
        return Optional.ofNullable(list).orElseGet(Lists::newArrayList);
    }

    public static <T> List<T> listNonNullOf(final @Nullable Collection<T> list) {
        return listOf(list).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> List<T> listNonNullOf(final @Nullable T... items) {
        return listOf(items).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @SafeVarargs
    @NotNull
    public static <T> List<T> listOf(@Nullable final T... items) {
        return items == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(items));
    }

    @SafeVarargs
    @NotNull
    public static <T, R> List<R> listOf(Function<T, R> keyProvider, @Nullable final T... items) {
        return items == null ? new ArrayList<>() : new ArrayList<>(Arrays.asList(items).stream().map(keyProvider).collect(Collectors.toList()));
    }
    /**
     *
     * @return a modifiable list that contains all input value.
     */
    @NotNull
    public static <T, R> List<R> listOf(@Nullable final Collection<T> collection, Function<T, R> keyProvider) {
        return collection == null ? listOf() : collection.stream().filter(Objects::nonNull).map(keyProvider).collect(Collectors.toList());
    }
    @NotNull
    public static <T, R> List<R> listNonNullOf(@Nullable final Collection<T> collection, Function<T, R> keyProvider) {
        return collection == null ? listOf() : collection.stream().filter(Objects::nonNull).map(keyProvider).filter(Objects::nonNull).collect(Collectors.toList());
    }

    /**
     * Get all items of other collections and put it into a list.
     * @return a modifiable list that contains all items of other collection.
     */
    @NotNull
    public static <T> List<T> listOf(@Nullable final Collection<T> collection) {
        return CollectionUtils.isEmpty(collection) ? Lists.newArrayList() : new ArrayList<>(collection);
    }

    public static <T> List<T> removeNullFrom(final List<T> list) {
        return emptyListIfNull(list).stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    /**
     * @return a modifiable linked set that contains all input value.
     */
    @SafeVarargs
    @NotNull
    public static <T> Set<T> setOf(@Nullable final T... items) {
        return items == null ? new HashSet<>() : new HashSet<>(Arrays.asList(items));
    }
    /**
     * Get all items of other collections and put it into a list.
     * @return a modifiable list that contains all items of other collection.
     */
    @NotNull
    public static <T> Set<T> setOf(@Nullable final Collection<T> collection) {
        return CollectionUtils.isEmpty(collection) ? Sets.newHashSet() : new HashSet<>(collection);
    }

    public static <T, R> Set<R> setOf(@Nullable final Collection<T> collection, Function<T, R> keyProvider) {
        return collection == null ? setOf() : collection.stream().filter(Objects::nonNull).map(keyProvider).collect(Collectors.toSet());
    }

    public static <T, R> Set<R> setNonNullOf(@Nullable final Collection<T> collection, Function<T, R> keyProvider) {
        return collection == null ? setOf() : collection.stream().filter(Objects::nonNull).map(keyProvider).filter(Objects::nonNull).collect(Collectors.toSet());
    }

    public static <T> Set<T> setNonNullOf(@Nullable final Collection<T> collection) {
        return collection == null ? setOf() : collection.stream().filter(Objects::nonNull).collect(Collectors.toSet());
    }
    /**
     * @return a modifiable linked set that contains all input value.
     */
    @SafeVarargs
    @NotNull
    public static <T> Set<T> linkedSetOf(@Nullable final T... items) {
        return items == null ? new LinkedHashSet<>() : new LinkedHashSet<>(Arrays.asList(items));
    }

    public static <K, E> Map<K, List<E>> group(Collection<E> items, Function<E, K> keyProvider) {
        return listOf(items).stream().collect(Collectors.groupingBy(keyProvider));
    }

    /**
     * Transform from list to map.
     * This method will prevent exception due to duplicated key by ignore the duplicated ones.
     */
    public static <E, KEY, VALUE> Map<KEY, VALUE> mapOf(@Nullable final Collection<E> list, @NotNull final Function<E, KEY> keyProvider, @NotNull final Function<E, VALUE> valueProvider) {
        return listOf(list).stream().collect(Collectors.toMap(keyProvider, valueProvider, (origin, dup) -> origin));
    }


    /**
     * Transform from list to map with the key that meet desired.
     * This method will prevent exception due to duplicated key by ignore the duplicated ones.
     */
    public static <K, V> Map<K, V> mapOf(@Nullable final Collection<V> list, @NotNull final Function<V, K> keyProvider) {
        return mapOf(list, keyProvider , element -> element);
    }

    /**
     * Get all items of other collections and put it into a map with provided key and value.
     * While extract key and value, this method will skip null key or null value.
     * This method will ignore the occurrences if found.
     * @param collection a collections. Can be null.
     * @param keyProvider how the key will be extracted. Must be not null.
     * @param valueProvider how the value will be extracted. Must be not null.
     * @return empty map if input collection is null or empty. Otherwise, return new map with both non-null key and value.
     * @throws IllegalArgumentException if keyProvider or valueProvider is null.
     */
    @NotNull
    public static <E, KEY, VALUE> Map<KEY, VALUE> nonNullMapOf(@Nullable final Collection<E> collection, @Nullable final Function<E, KEY> keyProvider, @Nullable final Function<E, VALUE> valueProvider) {
        Preconditions.checkNotNull(keyProvider, "Key provider must not be null.");
        Preconditions.checkNotNull(valueProvider, "Value provider must not be null.");

        if (isEmpty(collection)) {
            return Maps.newHashMap();
        }
        Function<E, KEY> memoizedKeyProvider = memoize(keyProvider);
        Function<E, VALUE> memoizedValueProvider = memoize(valueProvider);
        return collection.stream()
                .filter(item -> memoizedKeyProvider.apply(item) != null)
                .filter(item -> memoizedValueProvider.apply(item) != null)
                .collect(Collectors.toMap(keyProvider, valueProvider, (origin, duplicated) -> origin));
    }

    /**
     * Get all items of other collections and put it into a map with provided key and value.
     * While extract key and value, this method will skip null key or null value.
     * This method will ignore the occurrences if found.
     * @param collection a collections. Can be null.
     * @param keyProvider how the key will be extracted. Must be not null.
     * @return empty map if input collection is null or empty. Otherwise, return new map with value.
     * @throws IllegalArgumentException if keyProvider is null.
     */
    @NotNull
    public static <K, V> Map<K, V> nonNullMapOf(@Nullable final Collection<V> collection, @Nullable final Function<V, K> keyProvider) {
        return mapOf(collection, keyProvider, item -> item);
    }

    // Visit following link for more information: https://stackoverflow.com/a/49634611
    private static <K, V> Function<K, V> memoize(Function<K, V> provider) {
        Map<K, V> map = Maps.newHashMap();
        return key -> map.computeIfAbsent(key, provider);
    }

    /**
     * Get next element after certain index in list
     * @return empty if list is empty. Otherwise, return optional of next element
     * If current element is in the end of the list, return the first one.
     */
    public static <T> Optional<T> nextOf(int currentIndex, List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Optional.empty();
        }
        if (currentIndex >= list.size()) {
            currentIndex = 0;
        }
        return Optional.ofNullable(list.get(currentIndex));
    }

    /**
     * Get next element after certain index in list
     * @return empty if list is empty. Otherwise, return optional of next element
     * If current element is in the end of the list, return the first one.
     */
    public static <T> Optional<T> lastElementMatch(Predicate<T> condition, List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Optional.empty();
        }
        List<T> listThatMeetCondition = list.stream().filter(condition).collect(Collectors.toList());
        return lastElementOf(listThatMeetCondition);
    }

    /**
     * Return next element of certain element in the list
     * @return empty if list is empty. Otherwise, return optional of next element.
     * If current element is in the end of the list, return the first one.
     */
    public static <T> Optional<T> nextOf(T element, List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Optional.empty();
        }
        int index = list.indexOf(element);
        return nextOf(index, list);
    }

    /**
     * Get the last element of a list.
     * @param list can be null or empty
     * @return empty if list is empty. Otherwise, return last element.
     */
    public static <T> Optional<T> lastElementOf(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(list.size() - 1));
    }

    public static <T> Optional<T> elementAt(int index, List<T> list) {
        try {
            return Optional.of(list.get(index));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Concatenate two list into a new list.
     * <pre> Example:
     * - left: [A, A, B, B, null]
     * - right: [B, B, C, C, null]
     * - return: [A, A, B, B, null, B, B, C, C, null]
     * </pre>
     * @param head the begin of the new list. Can be null.
     * @param tail the end of the new list. Can be null.
     * @return new merged list of 2 input list.
     */
    @NotNull
    public static <T> List<T> union(@Nullable final Collection<T> head, @Nullable final Collection<T> tail) {
        if (isEmpty(head)) {
            return listOf(tail);
        }
        if (isEmpty(tail)) {
            return listOf(head);
        }
        List<T> result = listOf(head);
        result.addAll(tail);
        return listOf(result);
    }

    /**
     * Merge two list into a new list by adding elements which are not existed in the head into the head.
     * <pre> Example:
     * - left: [A, A, B, B, null]
     * - right: [B, B, C, C, null]
     * - return: [A, A, B, B, null, C, C]
     * </pre>
     * @param head the begin of the new list. Can be null.
     * @param tail the end of the new list. Can be null.
     * @return new merged list of 2 input list.
     */
    @NotNull
    public static <T> List<T> merge(@Nullable final Collection<T> head, @Nullable final Collection<T> tail) {
        if (isEmpty(head)) {
            return listOf(tail);
        }
        if (isEmpty(tail)) {
            return listOf(head);
        }

        List<T> headList = listOf(head);
        Set<T> headSet = Sets.newHashSet(head);

        List<T> itemsThatNotExistedInHead = tail.stream().filter(item -> !headSet.contains(item)).collect(Collectors.toList());
        headList.addAll(itemsThatNotExistedInHead);

        return headList;
    }

    /**
     * Get a list contains all elements existing in only the left list (including null).
     * <pre> Example:
     * - left: [A, A, B, B, null]
     * - right: [B, B, C, C, null]
     * - return: [A, A]
     * </pre>
     * @param left first list, can be null.
     * @param right second list, can be null.
     */
    @NotNull
    public static <T> List<T> inLeftOnly(@Nullable final Collection<T> left, @Nullable final Collection<T> right) {
        Set<T> leftSet = Sets.newHashSet(listOf(left));
        Set<T> rightSet = Sets.newHashSet(listOf(right));

        Predicate<T> elementsInLeftOnly = element -> leftSet.contains(element) && !rightSet.contains(element);

        return union(left, right)
                .stream()
                .filter(elementsInLeftOnly)
                .collect(Collectors.toList());
    }

    /**
     * Get a list contains all elements existing in only the right list (including null).
     * <pre> Example:
     * - left: [A, A, B, B, null]
     * - right: [B, B, C, C, null]
     * - return: [C, C]
     * </pre>
     * @param left first list, can be null.
     * @param right second list, can be null.
     */
    @NotNull
    public static <T> List<T> inRightOnly(@Nullable final Collection<T> left, @Nullable final Collection<T> right) {
        Set<T> leftSet = Sets.newHashSet(listOf(left));
        Set<T> rightSet = Sets.newHashSet(listOf(right));

        Predicate<T> elementsInRightOnly = element -> !leftSet.contains(element) && rightSet.contains(element);

        return union(left, right)
                .stream()
                .filter(elementsInRightOnly)
                .collect(Collectors.toList());
    }

    /**
     * Get a list contains all elements existing in both list (including null).
     * <pre> Example:
     * - left: [A, A, B, B, null]
     * - right: [B, B, C, C, null]
     * - return: [B, B, null]
     * </pre>
     * @param left first list, can be null.
     * @param right second list, can be null.
     */
    @NotNull
    public static <T> List<T> inBoth(@Nullable final Collection<T> left, @Nullable final Collection<T> right) {
        Set<T> rightSet = Sets.newHashSet(listOf(left));

        Predicate<T> elementsThatAlsoInTheRight = rightSet::contains;

        return listOf(right)
                .stream()
                .filter(elementsThatAlsoInTheRight)
                .collect(Collectors.toList());
    }

    public static <T> int sizeOf(T[] array) {
        return array == null ? 0 : array.length;
    }

    @SafeVarargs
    @NotNull
    public static <T> List<T> inBoth(@Nullable Collection<T>... collections) {
        int size = sizeOf(collections);
        if (size == 0) {
            return listOf();
        }
        assert collections != null;
        if (size == 1) {
            return listOf(collections[0]);
        }
        if (size == 2) {
            return inBoth(collections[0], collections[1]);
        }
        List<T> result = listOf(collections[0]);
        for (int i = 1; i < size; i++) {
            result = inBoth(result, collections[i]);
        }
        return result;
    }


    public static <T> Set<T> emptySetIfNull(final @Nullable Set<T> list) {
        return Optional.ofNullable(list).orElseGet(Sets::newHashSet);
    }

    public static <T> void moveToHeadOf(@Nullable List<T> list, @Nullable T newHead) {
        if (newHead == null || list == null) {
            return;
        }
        list.remove(newHead);
        list.add(0, newHead);
    }
}
