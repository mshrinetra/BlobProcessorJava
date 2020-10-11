package com.mshri;

import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.OutputBinding;
import com.microsoft.azure.functions.annotation.BindingName;
import com.microsoft.azure.functions.annotation.BlobOutput;
import com.microsoft.azure.functions.annotation.BlobTrigger;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.StorageAccount;

/**
 * A Blob trigger function for file processing
 */
public class Function {
    /**
     * This function triggers when a file is uploaded to a container named
     * "uploadedfiles" in Azure Blob.
     */
    @FunctionName("BlobProcessorJava")
    @StorageAccount("AzureWebJobsStorage")
    public void run(
            @BlobTrigger(name = "file", dataType = "binary", path = "uploadedfiles/{blobname}") byte[] inputContent,
            @BindingName("blobname") String fileName,
            @BlobOutput(name = "target", path = "processedfiles/processed-{blobname}") OutputBinding<String> outputItem,
            final ExecutionContext context) {
        String inputText = new String(inputContent);
        String processedText = MyFileProcessor.sortAndSerialize(inputText, 4, 4);
        outputItem.setValue(processedText);

        context.getLogger().info("Name: " + fileName + ", Size: " + inputContent.length + " bytes");
    }
}
