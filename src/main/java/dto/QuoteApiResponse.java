package dto;

import lombok.Data;

import java.util.List;

@Data
public class QuoteApiResponse {

    private QuoteDto[] quotes;

  //  private List<QuoteDto> quotes;


    public QuoteDto[] getQuotes() {
        return quotes;
    }

    public void setQuote(QuoteDto[] quotes){
        this.quotes=quotes;
    }
}
