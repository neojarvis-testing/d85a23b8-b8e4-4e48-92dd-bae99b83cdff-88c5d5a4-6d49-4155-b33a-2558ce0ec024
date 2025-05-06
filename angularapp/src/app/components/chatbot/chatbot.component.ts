import { Component } from '@angular/core';

@Component({
  selector: 'app-chatbot',
  templateUrl: './chatbot.component.html',
  styleUrls: ['./chatbot.component.css']
})
export class ChatbotComponent {
  showChatbot: boolean = false;
  isMaximized: boolean = false;
  userMessage: string = "";
  selectedQuestion: string = "";
  chatHistory: { sender: string, text: string }[] = [];

  predefinedQuestions: string[] = [
    "How can I buy a property?",
    "What are the best locations to invest?",
    "How do I get a property valuation?",
    "What are the hidden costs in buying a house?",
    "What are my tenant rights?",
    "How can I contact an estate agent?",
    "How do property taxes work?",
    "What factors affect property value?",
    "How do I negotiate the price when buying a house?",
    "What are the benefits of hiring a property manager?",
    "How do I check if a property has legal issues before purchasing?",
    "Should I buy or rent a house?"
];

responses: { [key: string]: string } = {
    "how can i buy a property?": "Start by browsing listings, securing financing, and working with a trusted real estate agent.",
    "what are the best locations to invest?": "Hotspots include growing urban areas, commercial hubs, and tourist-friendly regions.",
    "how do i get a property valuation?": "You can hire a certified valuer, use real estate agency services, or check online appraisal tools.",
    "what are the hidden costs in buying a house?": "Consider registration fees, taxes, inspection costs, legal charges, and future maintenance expenses.",
    "what are my tenant rights?": "Tenants have legal rights related to fair rent, security deposits, property condition, and lease agreements.",
    "how can i contact an estate agent?": "Search online directories, visit real estate offices, or use property portals to find licensed professionals.",
    "how do property taxes work?": "Property taxes depend on the location, property value, and government policies. Always check tax regulations before purchasing.",
    "what factors affect property value?": "Key factors include location, infrastructure developments, nearby amenities, neighborhood reputation, and market demand.",
    "how do i negotiate the price when buying a house?": "Start with market research, highlight needed repairs, make a reasonable initial offer, and use strategic counteroffers.",
    "what are the benefits of hiring a property manager?": "Property managers handle tenant screening, rent collection, legal compliance, and maintenance, reducing your workload.",
    "how do i check if a property has legal issues before purchasing?": "Verify title deeds, land records, encumbrance certificates, building approvals, and consult a real estate lawyer for thorough checks.",
    "should i buy or rent a house?": "Buying offers long-term investment benefits, while renting provides flexibility. Choose based on financial stability and lifestyle needs.",
    "hello": "Hi there! How can I assist you?",
    "hi": "Hello! How may I help?",
    "hey": "Hey there! Need assistance?",
    "bye": "Goodbye! Have a great day!",
    "how are you": "I'm just a chatbot, but I'm here to help you with property-related queries!",

    // Property Listings & Buying
    "property": "You can browse properties in the listings section!",
    "available properties": "You can check our latest listings in the 'Properties' section.",
    "luxury properties": "We offer premium and luxury estates. Check our high-end listings!",
    "budget properties": "Looking for affordable options? Browse our budget-friendly listings!",
    "how to buy a property": "Start by browsing listings, then contact an agent to assist you with the process.",
    "property price range": "Prices vary depending on location and type. You can filter based on your budget.",
    "best locations to buy": "Popular locations include downtown, coastal areas, and commercial hubs.",
    "new properties": "We update our listings regularly. Check our 'New Arrivals' section!",
    "property financing": "You can apply for mortgages or loans. We have expert advisors to guide you.",
    "property investment opportunities": "Investing in real estate is profitable! Contact us for market insights.",

    // Selling a Property
    "how to sell a property": "List your property with us, set a price, and we'll connect you with buyers!",
    "how to get a property valuation": "Our experts can evaluate your property to determine its market value.",
    "how fast can I sell my property": "It depends on demand, price, and marketing. We ensure fast sales!",
    "best time to sell property": "High demand seasons, like spring and summer, are ideal for selling.",
    "selling process": "List your property, attract buyers, negotiate prices, and complete legal formalities.",

    // Real Estate Agents
    "trusted agents": "Work with our verified real estate professionals for smooth transactions.",
    "find a real estate agent": "Our team of expert agents is here to assist you with buying or selling!",
    "how can an agent help me": "Agents help with pricing, negotiation, paperwork, and closing deals.",
    "agent commission": "Agent commissions vary, typically around 2-6% of the sale price.",

    // Renting & Leasing
    "how to rent a property": "Browse rental listings, contact landlords, and finalize agreements.",
    "best places to rent": "Popular rental areas include urban centers and locations with high demand.",
    "long-term rental vs short-term": "Long-term rentals provide stability, while short-term offers flexibility.",
    "rental agreements": "Ensure you review lease terms, duration, deposit, and tenant responsibilities.",
    "landlord responsibilities": "Landlords must maintain property conditions, ensure legal compliance, and handle tenant requests.",
    "tenant rights": "Tenants have rights regarding fair rent, security deposits, and safe living conditions.",

    // Property Management
    "property management services": "We offer management solutions for landlords and investors!",
    "how to maintain property": "Regular inspections, repairs, and tenant communication keep properties in top shape.",
    "hire property manager": "A property manager can help handle tenant issues, maintenance, and legal matters.",
    "vacant property solutions": "We provide marketing strategies to rent or sell vacant properties quickly.",

    // Legal & Documentation
    "property legal requirements": "Legal checks, title verification, and tax documentation are essential.",
    "title deeds": "Ensure clear ownership with proper title deeds before buying property.",
    "property taxes": "Taxes vary based on location, property value, and government policies.",
    "documents required for buying property": "You'll need ID proof, financial records, sale agreements, and registration papers.",

    // Investment & Market Trends
    "real estate investment tips": "Diversify your portfolio, buy in high-demand areas, and consult experts!",
    "best cities for real estate investment": "Hotspots include growing urban areas and tourist-friendly regions.",
    "property appreciation": "Properties appreciate based on location, development, and infrastructure.",
    "market trends": "Real estate markets fluctuate; we provide expert analysis on buying/selling opportunities.",
    "ROI on property investment": "Rental yields and resale value determine the return on investment.",

    // General FAQs
    "how to contact estate connect": "Visit our contact page for details!",
    "customer support": "Our team is available for assistance. Reach out to us anytime!",
    "property insurance": "Protect your investment with suitable insurance plans.",
    "mortgage rates": "Rates vary based on the lender and financial conditions.",
    "property fraud prevention": "Verify documents, deal with licensed agents, and conduct due diligence.",
    "how to negotiate property price": "Research market rates, compare listings, and present reasonable offers.",
    "hidden costs in property buying": "Consider taxes, maintenance fees, legal charges, and commissions."
  };

  toggleChatbot(): void {
    this.showChatbot = !this.showChatbot;
  }

  toggleSize(): void {
    this.isMaximized = !this.isMaximized;
  }

  clearChat(): void {
    this.chatHistory = [];
  }

  sendMessage(): void {
    if (!this.userMessage.trim()) return;

    this.chatHistory.push({ sender: "User", text: this.userMessage });

    const formattedMessage = this.userMessage.toLowerCase().trim();
    const botResponse = this.responses[formattedMessage] || "I'm not sure, but I can help you explore property options!";
    this.chatHistory.push({ sender: "EstateBot", text: botResponse });

    this.userMessage = "";
  }

  sendSelectedQuestion(): void {
    if (!this.selectedQuestion.trim()) return;

    this.chatHistory.push({ sender: "User", text: this.selectedQuestion });

    const formattedQuestion = this.selectedQuestion.toLowerCase().trim();
    const botResponse = this.responses[formattedQuestion] || "I'm not sure, but feel free to browse our FAQs!";
    this.chatHistory.push({ sender: "EstateBot", text: botResponse });

    this.selectedQuestion = ""; // Reset dropdown selection
  }
}
