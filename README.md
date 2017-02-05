# hibernate5
Using Hibernate 5 new features: <P>
This project uses the new JPA criteria query which is so over engineered that it is now worthless.  I liked to old hibernate criteria query which couldn't do everything but worked in most cases and was nice to use.  And the meta model classes...  seriously?  bleah!  Time to incorporate generics into the JVM, no more erasure.  Screw Java 1.4<p>
It also uses Mapstruct to map from JPA entites to a proxy free DTO objects which is necessary for running your object graph thru the JAXB processor and not triggering lazy load exceptions.  It maps to the same classes you just have to manually ignore all of the unintialized proxys and collections.  Although JAXB seems to want the @XmlElementWrapper for collections of objects, so if that is not there then JAXB will ignore them anyway.  But don't get burned by this later, probably better to still put the ignore annotations in your mapstruct interface file. <P>
Also tried to use Dozer to purge proxies but couldn't get it to work.  Dozer seems dead anyway. <P>
Also uses a query read only hint which makes hibernate more efficient query.setHint("org.hibernate.readOnly", true) <P>
The collections in the entity classes are of type List.  It is nice since results are returned in the same order as they are in the database.  The downside is you can only eager load one collection at a time, more than one fetch join yields: <br>
java.lang.IllegalArgumentException: org.hibernate.loader.MultipleBagFetchException: cannot simultaneously fetch multiple bags:<br>
It can be done with Sets since they can naturally handle dupulicates, but not Lists.
