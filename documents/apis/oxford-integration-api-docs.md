# DefaultApi

All URIs are relative to *https://oxford_integration*

Method | HTTP request | Description
------------- | ------------- | -------------
[**translate**](oxford-integration-api-docs.md#translate) | **GET** /translate | GET translate
[**translateRaw**](oxford-integration-api-docs.md#translateRaw) | **GET** /translate/raw | GET translate/raw


<a name="translate"></a>
# **translate**
> translate(sourceLang, targetLang, workId)

GET translate

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://oxford_integration");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String sourceLang = "sourceLang_example"; // String | 
    String targetLang = "targetLang_example"; // String | 
    String workId = "workId_example"; // String | 
    try {
      apiInstance.translate(sourceLang, targetLang, workId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#translate");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sourceLang** | **String**|  |
 **targetLang** | **String**|  |
 **workId** | **String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

<a name="translateRaw"></a>
# **translateRaw**
> translateRaw(sourceLang, targetLang, workId)

GET translate/raw

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.models.*;
import org.openapitools.client.api.DefaultApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://oxford_integration");

    DefaultApi apiInstance = new DefaultApi(defaultClient);
    String sourceLang = "sourceLang_example"; // String | 
    String targetLang = "targetLang_example"; // String | 
    String workId = "workId_example"; // String | 
    try {
      apiInstance.translateRaw(sourceLang, targetLang, workId);
    } catch (ApiException e) {
      System.err.println("Exception when calling DefaultApi#translateRaw");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sourceLang** | **String**|  |
 **targetLang** | **String**|  |
 **workId** | **String**|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | OK |  -  |

