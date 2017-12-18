By default Ignite is set to 2.2.0

Start MainApp class and go to http://localhost:8080/
You'll see that's SampleEntity date is not changing so it's cached.

Change Ignite version to 2.3.0.

Enter http://localhost:8080/ twice. For the first time it's working. Second time is crashing. 
 From other project stacktrace is 
 
 
 field XXX is List<DatastoreKey> type
```

Caused by: org.apache.ignite.binary.BinaryObjectException: Failed to read field [name=XXXX]
	at org.apache.ignite.internal.binary.BinaryFieldAccessor.read(BinaryFieldAccessor.java:168) ~[ignite-core-2.3.0.jar:2.3.0]
	at org.apache.ignite.internal.binary.BinaryClassDescriptor.read(BinaryClassDescriptor.java:843) ~[ignite-core-2.3.0.jar:2.3.0]
	... 135 more
Caused by: java.lang.NullPointerException
	at com.google.common.collect.ImmutableList.hashCode(ImmutableList.java:571) ~[guava-20.0.jar:?]
	at java.util.Arrays.hashCode(Arrays.java:4146) ~[?:1.8.0_152]
	at java.util.Objects.hash(Objects.java:128) ~[?:1.8.0_152]
	at com.google.cloud.datastore.BaseKey.hashCode(BaseKey.java:204) ~[google-cloud-datastore-1.8.0.jar:1.8.0]
	at com.jmethods.catatumbo.DefaultDatastoreKey.hashCode(DefaultDatastoreKey.java:134) ~[catatumbo-catatumbo-2.4.0.jar:2.4.0]
	at java.util.HashMap.hash(HashMap.java:339) ~[?:1.8.0_152]
	at java.util.HashMap.put(HashMap.java:612) ~[?:1.8.0_152]
	at java.util.HashSet.add(HashSet.java:220) ~[?:1.8.0_152]
	at org.apache.ignite.internal.binary.BinaryUtils.doReadCollection(BinaryUtils.java:2093) ~[ignite-core-2.3.0.jar:2.3.0]
	at org.apache.ignite.internal.binary.BinaryReaderExImpl.deserialize0(BinaryReaderExImpl.java:1914) ~[ignite-core-2.3.0.jar:2.3.0]
	at org.apache.ignite.internal.binary.BinaryReaderExImpl.deserialize(BinaryReaderExImpl.java:1714) ~[ignite-core-2.3.0.jar:2.3.0]
	at org.apache.ignite.internal.binary.BinaryReaderExImpl.readField(BinaryReaderExImpl.java:1982) ~[ignite-core-2.3.0.jar:2.3.0]
	at org.apache.ignite.internal.binary.BinaryFieldAccessor$DefaultFinalClassAccessor.read0(BinaryFieldAccessor.java:679) ~[ignite-core-2.3.0.jar:2.3.0]
	at org.apache.ignite.internal.binary.BinaryFieldAccessor.read(BinaryFieldAccessor.java:164) ~[ignite-core-2.3.0.jar:2.3.0]
	at org.apache.ignite.internal.binary.BinaryClassDescriptor.read(BinaryClassDescriptor.java:843) ~[ignite-core-2.3.0.jar:2.3.0]
	... 135 more

```