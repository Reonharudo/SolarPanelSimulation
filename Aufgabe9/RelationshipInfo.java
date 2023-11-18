import java.lang.annotation.*;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(RelationshipInfos.class)
public @interface RelationshipInfo {
    String reason();
    Class<?> notInRelationshipWith();
}
