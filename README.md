## GraphQL Practice

This practice focuses on building a GraphQL backend in a Spring-based environment and organizing it in a way that reflects how GraphQL is actually introduced and evolved in real services. The goal is not just to execute queries, but to understand how GraphQL fits into backend architecture, how schemas drive the API, and how execution flows through resolvers and domain logic.

Unlike REST APIs that are typically controller-centric, GraphQL centers around schemas and resolvers. Requests are executed at the field level rather than the endpoint level. This practice is designed to internalize those differences through concrete code structure rather than abstract theory.

---

### Development Environment

- Mac Sonoma
- IntelliJ IDEA
- OpenJDK 

---

### Spring + GraphQL Stack

The following dependencies are used to support a GraphQL backend that goes beyond basic query execution and covers persistence, security, testing, and execution control.

- org.springframework.boot:spring-boot-starter-data-jpa
- org.springframework.boot:spring-boot-starter-graphql
- org.springframework.boot:spring-boot-starter-web
- org.projectlombok:lombok
- com.h2database:h2
- org.springframework.boot:spring-boot-starter-test
- org.springframework:spring-webflux
- org.springframework.graphql:spring-graphql-test
- com.graphql-java:graphql-java-extended-scalars:21.0
- org.springframework.boot:spring-boot-starter-security

---

### Architecture Overview

The GraphQL server follows a layered Spring backend structure while adapting to GraphQL’s execution model.

- Schema Layer  
  Defines the GraphQL API contract. Queries, mutations, and types describe what clients can request and serve as the single source of truth for the API surface.

- Resolver Layer  
  Acts as the entry point for GraphQL execution. Resolvers bind schema fields to backend logic and delegate actual processing to domain or service layers instead of containing business logic themselves.

- Domain / Service Layer  
  Contains business rules and orchestration logic. This layer is kept independent of GraphQL to avoid coupling core logic to a specific API interface.

- Persistence Layer  
  Handles data access using JPA repositories. Database concerns are isolated from the schema and resolver layers to maintain clear boundaries.

This separation ensures that GraphQL-specific concerns do not leak into core domain logic and allows the system to evolve more safely over time.

---

### Practice Flow

The practice progresses incrementally, similar to how GraphQL is typically adopted in an existing backend.

- Initial GraphQL server setup and baseline configuration
- Schema definition and integration with domain and persistence models
- Use of directives for cross-cutting concerns such as authentication
- Instrumentation for observing execution flow and performance
- Organizing the GraphQL layer around the schema as the primary contract

---

### Practice Areas

#### GraphQL Server Setup and Configuration

This phase establishes the GraphQL runtime within a Spring application and clarifies the surrounding Java GraphQL ecosystem.

- Overview of major Java-based GraphQL libraries and their evolution
  - graphql-java / graphql-java-spring (archived)
    https://github.com/graphql-java/graphql-java-spring
  - graphql-java-kickstart / graphql-spring-boot (archived)
    https://github.com/graphql-java-kickstart/graphql-spring-boot
  - spring-projects / spring-graphql
    https://github.com/spring-projects/spring-graphql
  - Netflix / dgs-framework
    https://github.com/Netflix/dgs-framework

- Project setup using spring-graphql
- Validation of basic GraphQL execution using a simple Version query

---

#### Schema Definition and Database Integration

This phase focuses on schema-first design and its relationship to underlying data models.

- Definition of GraphQL types, queries, and mutations
- Reuse of an existing domain model originally designed for REST APIs
- Mapping between GraphQL types and JPA entities
- Resolver-driven data access with clear separation from persistence concerns

---

#### GraphQL Directives

Directives are introduced to handle cross-cutting concerns at the schema level.

- Definition of authentication-related directives
- Integration with Spring Security
- Comparison with REST-style filters and interceptors
- Field-level authorization and access control patterns

---

#### GraphQL Instrumentation

Instrumentation is used to observe and analyze how GraphQL requests are executed.

- Measuring query execution time
- Logging field-level execution behavior
- Inspecting resolver invocation order and execution cost
- Understanding the GraphQL execution pipeline and extension points

---

#### GraphQL Structure and Organization

The final phase focuses on organizing the GraphQL layer around the schema as the central contract.

- Using the schema as the primary reference for available operations
- Keeping schema definitions and resolver implementations aligned
- Ensuring the GraphQL layer remains predictable and maintainable
- Treating GraphQL as a stable service interface rather than a thin transport layer
