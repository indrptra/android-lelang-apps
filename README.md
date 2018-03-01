# Base Project
Developer should write what the application is here. For better viewing, I will paste lorem ipsum here. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent dapibus ligula arcu, sed sagittis arcu imperdiet quis. Vivamus varius vestibulum imperdiet. Nunc vel porttitor dui. Nunc ultricies dictum magna id hendrerit. Nulla non lorem orci. In mollis venenatis libero vitae pretium. Vestibulum egestas tortor ac odio efficitur, consequat fermentum metus mattis. Nulla dictum, turpis ut consequat maximus, massa nibh rutrum sem, a feugiat enim odio quis ex. Maecenas faucibus porttitor aliquet. In eget ornare velit, sit amet pellentesque eros. 

# Latest PlayStore APK ([link](https://play.google.com/store/apps/details?id=com.android.chrome))
* 25-Jul-2099, build 7 - `v2.1`

# Update History ([link](UPDATES.md))

# Credential Information ([link](CREDENTIALS.md))

# Developer Notes
* Below are the steps for implementing a page that has a simple list of item:
  * Create a RecyclerView adapter class that extends from `DataListRecyclerViewAdapter`
     * Specify your data type and view holder (as generics when extending), the overall implementation doesn't differ from normal `RecyclerView.Adapter` implementation
     * The only difference is the overridden methods:
         * `onCreateDefaultViewHolder` instead of `onCreateViewHolder`
         * `onBindDefaultViewHolder` instead of `onBindViewHolder`
     * This adapter can manage empty, error (whole page / pagination), and progress (whole page / pagination) views
         * To override the ViewHolder for each type, simply override the create and bind methods for the required type.
         * e.g. Override `onCreateEmptyViewHolder` and `onBindEmptyViewHolder` for empty layout
  * Create a Fragment class that extends from `DataListFragment`
     * This fragment is capable to handle error, retries, swipe-to-refresh and infinite scrolling behaviour
         * To change their behaviour, call `enableSwipeToRefresh()` or `enableInfiniteScrolling()` (disable method is also available)
     * Override `initRecyclerAdapter()` and return your required Adapter
     * Override `initRecyclerLayoutManager()` and return your required LayoutManager
         * Pagination load currently only supports LinearLayoutManager and GridLayoutManager, for other LayoutManager, you can specify the logic for it in the `DataListFragment` class
     * Override `fetchData()` with your logic to obtain the list of data for the adapter
         * To update the view and data, call `updateResource(resource)`
         * Calling `updateResource(Resource.loading())` will cause the app to display a loading progress
         * Calling `updateResource(Resource.success(newList))` will cause the app to add the new list to the existing list and display it.
             * To change this addition behaviour, override `addCollection()` and change the logic accordingly
         * Calling `updateResource(Resource.error(appError))` will cause the app to display an error
     * <b>NOTES:</b> As of <i>20-Jun-2017</i>, this base Fragment class no longer separates the data fetch function to load from a separate source (e.g. local and remote). Such methods should be specified by children classes themselves.
  * Create an Activity class that extends from `DataListActivity`
     * Override `initDataFragment()` and return your required `DataListFragment` classMust be called by the `obtainDataFromRemote()` after the data is obtained.
  * Sample usage can be seen in the source code.

# Additional Information
The major differences between debug and release build are:

* Log messages
  * Only debug builds will show log messages.
* Detailed messages
  * More detailed messages will only show up on debug builds.
  * Release builds will only show simpler messages.
* App version in splash screen
  * Debug builds will show the detailed app version code, version name, and build date on splash screen.
  * Release builds will only show the app version name on splash screen.
* Unhandled exception Activity
  * Debug builds will show the error stacktrace, and allows the user to share the stacktrace.
  * Release builds will only show the usual error message and the restart app button.
* <i><span style="color:green">TODO: Add more differences here</span></i>

# Known bugs / problem:
* Nothing!

# TODO list:
* <i><span style="color:green">TODO: Add your TODO lists here</span></i>