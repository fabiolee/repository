# repository
A library that uses Repository Pattern with a strategy that, through a loader, picks different sources depending on certain conditions.

For example, when getting a data by id, the file cache will be selected if the user already exists in cache, otherwise the cloud will be queried to retrieve the data and later save it to the file cache.

## Download
Download via Gradle:
```
compile 'com.fabiolee.repository:repository:1.0.1'
```

## Usage
Load xml:
```
Repository.with(context)
        .loadXml(url, App.class)
        .defaultAsset("app.xml")
        .callback(new Callback<App>() {
            @Override
            public void onResponse(App object) {
                ...
            }
        });
```
Load image:
```
Repository.with(context)
        .loadImage(url, imageView);
```