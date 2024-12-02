# SimpleGraph
[
![JitPack Link](https://img.shields.io/jitpack/version/com.github.TomCo2210/25A-10221-L02)
](https://github.com/TomCo2210/25A-10221-L02)

The **SimpleGraph Android Library** is a lightweight and easy-to-use library for drawing **line graphs** in Android applications. Its primary functionality is to visualize a set of data values on the Y-axis, allowing developers to represent data trends in a simple, visually appealing way.

### **Key Features**

1.  **Customizable Line Graph**:

    -   Draws line graphs based on a given set of values.

2.  **Data Input**:

    -   Accepts an array of data points (e.g., `List<Float>` or `FloatArray`).
    -   Dynamically renders the graph based on the provided dataset.
3.  **Lightweight and Minimalistic**:

    -   Focused solely on drawing line graphs without additional complexity.
    -   Ideal for applications needing simple data visualization.
4.  **Scalable and Responsive**:

    -   Adapts to various screen sizes.
    -   Scales the graph to fit the provided view dimensions.

----------

### **How It Works**

1.  **Input**:

    -   The library accepts a set of values, typically represented as Float numbers. These values are supplied in an array or a list.

    Example:

    ```java
    Deque<Float> data = new LinkedList<>();
    data.add(val);
    //repeat as needed.
    ```

2.  **Rendering**:

    -   The library uses Android's `Canvas` and `Paint` classes to draw lines connecting the data points on a `CustomView`.
3.  **Display**:

    -   The graph is rendered within a `View`, which can be placed in any layout like `ConstraintLayout`, `LinearLayout`, or `RelativeLayout`.

----------

### **Basic Usage Example**

1.  **Add the JitPack Package Manager to Your Project**: If the library is available via a package manager like JitPack, include it in your `settings.gradle.kts`:

```kotlin
   dependencyResolutionManagement {
		...
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. **Add the Library to Your Project**: the library is available via a package manager like JitPack, include it in your `build.gradle.kts`:
```kotlin
	dependencies {
	        implementation ("com.github.TomCo2210:25A-10221-L02:$current_version")
	}
```

2.  **Add SimpleGraph to Your Layout**:
```xml
<dev.tomco.simplegraph.GraphView  
        android:id="@+id/main_GV_graph"  
        android:layout_width="300dp"
        android:layout_height="200dp"
        app:layout_centerInParent="true"
        android:padding="10dp"
        app:size="50"
        app:lineWidth="2dp" />
```

3.  **Provide Data to the Graph**:
```java
main_GV_graph.initGraph(this);  
main_GV_graph.setRanges(0, 50);  
for (int i = 0; i < 50; i++) {  
  main_GV_graph.addPoint(new Random().nextInt(50));  
}
```
----------

### **Applications**

-   **Data Visualization**:
    -   Display trends in analytics dashboards.
-   **Real-Time Updates**:
    -   Show live data like stock prices or sensor readings.
-   **Educational Tools**:
    -   Create interactive graphs for learning applications.