## Part 1
We run the program with inputs (4,7,9) and after profiling, we realize that `temp` function takes most of processing time.

<img width="1728" alt="se-lab8-1" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/177aba3f-34f0-48fa-838f-cd0d3ca44139">
<img width="1728" alt="se-lab8-2" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/5fcbcd59-ed93-445f-bf0b-c771c4bc4221">

After further investigations, it seems like we spend significant time in `add` function for our dynamic array.

<img width="1728" alt="se-lan8-3" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/5c9bafa7-2fd7-4060-9bb1-d5a8a77a559b">

So we change the code to allocate ram in the beginning:

```java
ArrayList a = new ArrayList(10000 * 20000);
```

<img width="1728" alt="se-lab8-4" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/23c71da9-895b-4afa-86e3-17f16f00f6a1">
<img width="1728" alt="se-lab8-5" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/02e2cca6-5362-4ecc-8bcb-cdd823f60c21">

We use a simple fixed size array for further improvement.

```java
int[] a = new int[10000 * 20000];
int index = 0;
for (int i = 0; i < 10000; i++)
{
    for (int j = 0; j < 20000; j++) {
        a[index] = i + j;
        index++;
    }
}
```

<img width="1728" alt="se-lab8-6" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/d25a39c1-c62f-4aae-81d8-11424a1d9717">
<img width="1728" alt="se-lab8-7" src="https://github.com/alirezaei1380/SE-LAB-8/assets/59190480/13bfb691-0e9a-4b72-93cc-503738e5bba0">

As you can see in the pictures, cpu time has improved significantly.
